package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.business.ad.AdBrandPossibiliites;
import fr.k2i.adnjoy.business.ad.Brand;
import fr.k2i.adnjoy.business.ad.Chanel;
import fr.k2i.adnjoy.business.ad.NoShowWith;
import fr.k2i.adnjoy.business.jackpot.JackPot;
import fr.k2i.adnjoy.business.jackpot.NetJackPot;
import fr.k2i.adnjoy.business.jackpot.TVJackPot;
import fr.k2i.adnjoy.business.jackpot.chanel.JackPotQuestion;
import fr.k2i.adnjoy.business.jackpot.chanel.Response;
import fr.k2i.adnjoy.business.webservice.TVAd;
import fr.k2i.adnjoy.business.webservice.TVAnswer;
import fr.k2i.adnjoy.business.webservice.TVBlindTest;
import fr.k2i.adnjoy.dao.AdDao;
import fr.k2i.adnjoy.dao.BrandDao;
import fr.k2i.adnjoy.dao.ChanelDao;
import fr.k2i.adnjoy.dao.JackPotDao;
import fr.k2i.adnjoy.manager.JackPotManager;
import fr.k2i.adnjoy.manager.Manager;

public class JackPotManagerImpl extends Manager<JackPotDao, JackPot, Long>
		implements JackPotManager {
	private AdDao adDao;
	private BrandDao brandDao;
	private ChanelDao chanelDao;

	public void setChanelDao(ChanelDao chanelDao) {
		this.chanelDao = chanelDao;
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public void setAdDao(AdDao adDao) {
		this.adDao = adDao;
	}

	@Override
//	@Scheduled(cron = "0 30 23 * * ?")
	public synchronized void generateDayJackPots() throws Exception {
		Calendar today = new GregorianCalendar();
		Calendar genDay = new GregorianCalendar(today.get(Calendar.YEAR), today
				.get(Calendar.MONTH), today.get(Calendar.DATE));
		genDay.add(Calendar.DATE, 1);
		generateDayJackPots(genDay, DEFAULT_AD, DEFAULT_POSSIBILITES);
	}

	private List<AdBrandPossibiliites> generateRandomAds(int nbAds,
			int nbPossibilities) throws Exception {
		List<AdBrandPossibiliites> res = new ArrayList<AdBrandPossibiliites>();
		Map<Integer, Ad> mapTest = new HashMap<Integer, Ad>();
		List<Ad> allAds = adDao.getAll();
		Random ramRandom = new Random();
		int i = 0;
		while (i <= nbAds) {
			int nextInt = ramRandom.nextInt(allAds.size());
			Ad ad = mapTest.get(nextInt);
			if (ad == null) {
				ad = allAds.get(nextInt);
				mapTest.put(nextInt, ad);
				res.add(generateAdBrandPossibilities(ad, nbPossibilities));
				i++;
			}
		}
		return res;
	}

	private AdBrandPossibiliites generateAdBrandPossibilities(Ad ad,
			int nbPossibilities) throws Exception {
		AdBrandPossibiliites res = new AdBrandPossibiliites();
		res.setAd(ad);
		res.setPossibilities(generatesPossibilities(ad, nbPossibilities));
		return res;
	}

	private void deleteNoPossible(List<Brand> pBrand, List<Brand> chooseBrands) {
		List<Brand> toRemove = new ArrayList<Brand>();

		for (Brand b : chooseBrands) {
			List<NoShowWith> no = b.getNoTogether();

			for (Brand brand : pBrand) {
				if (brand.getId().equals(b.getId())) {
					toRemove.add(brand);
				}
				List<NoShowWith> noTogether = brand.getNoTogether();
				for (NoShowWith noShowWith : noTogether) {
					if (noShowWith.getBrandDst().getId().equals(b.getId())
							|| noShowWith.getBrandSrc().getId().equals(b.getId())) {
						toRemove.add(b);
						toRemove.add(noShowWith.getBrandSrc());
					}
				}
				for (NoShowWith noShowWith : no) {
					if (noShowWith.getBrandDst().getId().equals(b.getId())
							|| noShowWith.getBrandSrc().getId().equals(b.getId())) {
						toRemove.add(b);
						toRemove.add(noShowWith.getBrandDst());
					}
				}
			}
		}

		for (Brand brand : toRemove) {
			pBrand.remove(brand);
		}
	}

	private boolean isPossible(List<Brand> pBrand, Brand b) {
		List<NoShowWith> no = b.getNoTogether();

		for (Brand brand : pBrand) {
			if (brand.getId().equals(b.getId())) {
//				 System.out.println(pBrand.toString()+ " "+b.toString());
				return false;
			}
			List<NoShowWith> noTogether = brand.getNoTogether();
			for (NoShowWith noShowWith : noTogether) {
				if (noShowWith.getBrandDst().getId().equals(b.getId())){
						//|| noShowWith.getBrandSrc().getId().equals(b.getId())) {
//					System.out.println(pBrand.toString() + " " + b.toString());
					return false;
				}
			}
			for (NoShowWith noShowWith : no) {
				if (noShowWith.getBrandDst().getId().equals(b.getId())){
						//|| noShowWith.getBrandSrc().getId().equals(b.getId())) {
//					System.out.println(pBrand.toString() + " " + b.toString());
					return false;
				}
			}
		}
		return true;
	}

	private List<Brand> generatesPossibilities(Ad ad, int nbPossibilities)
			throws Exception {
		List<Brand> brandRandom = new ArrayList<Brand>();
		List<Brand> pBrand = new ArrayList<Brand>();
		pBrand.add(ad.getBrand());
		List<Brand> brands = brandDao.findByTypeAd(ad.getType());
		// deleteNoPossible(brands,pBrand);

		if (brands.size() == 0) {
			brands = brandDao.getAll();
		}

		Random random = new Random();
		int whereCorrect = random.nextInt(nbPossibilities);
		// if(brands.size()<nbPossibilities){
		// brands = brandDao.getAll();
		// }
		for (int i = 0; i < nbPossibilities; i++) {
			if (i == whereCorrect) {
				brandRandom.add(ad.getBrand());
			} else {
				Random rb = new Random();
				int nextInt = rb.nextInt(brands.size());
				Brand b = brands.get(nextInt);
				// pBrand.add(b);
				if (isPossible(pBrand, b)) {
					brandRandom.add(b);
					pBrand.add(b);
				} else {
					// System.out.println("not possible "+ b.toString());
					i--;
				}

				deleteNoPossible(brands, pBrand);

				if (brands.size() == 0) {
					brands = brandDao.getAll();
				}
				// pBrand = new ArrayList<Brand>(brandRandom);

				// brandRandom.add(b);
				/*
				 * Random rb = new Random(); do{
				 * 
				 * int nextInt = rb.nextInt(brands.size()); Brand b =
				 * brands.get(nextInt); if(!b.equals(ad.getBrand())){
				 * if(!brandRandom.contains(b)){ brandRandom.add(b); brand = b;
				 * } }
				 * 
				 * }while(brand == null);
				 */
			}
		}
//		System.out.println(brandRandom.toString());
		return brandRandom;
	}

	@Override
	public JackPot getNextJackPot() throws Exception {
		return dao.getNextJackPot();
	}

	@Override
	public void generateDayJackPots(Calendar genDay, int nbAds,
			int nbPossibilities) throws Exception {
		Calendar now = new GregorianCalendar();
		Calendar calEnd = new GregorianCalendar(genDay.get(Calendar.YEAR),
				genDay.get(Calendar.MONTH), genDay.get(Calendar.DAY_OF_MONTH));
		calEnd.add(Calendar.DATE, 1);
		List<JackPot> js = dao.getByDay(genDay.getTime(), calEnd.getTime());
		if(js.size()>0)return;
//		for (JackPot jackPot : js) {
//			dao.delete(jackPot);
//		}

		int day = genDay.get(Calendar.DATE);
		while (day == genDay.get(Calendar.DATE)) {
			if (genDay.after(now)) {
				JackPot jackPot = new NetJackPot();
				jackPot.setAds(generateRandomAds(nbAds, nbPossibilities));
				jackPot.setStartDate(genDay.getTime());
				dao.save(jackPot);
			}
			genDay.add(Calendar.MINUTE, 2);
		}

	}

	@Override
	public void generateDayTVJackPots(Calendar genDay, int nbAds,
			int nbPossibilities) throws Exception {
		Calendar now = new GregorianCalendar();
		Calendar calEnd = new GregorianCalendar(genDay.get(Calendar.YEAR),
				genDay.get(Calendar.MONTH), genDay.get(Calendar.DAY_OF_MONTH));
		calEnd.add(Calendar.DATE, 1);
		List<JackPot> js = dao.getTVByDay(now.getTime(), calEnd.getTime());
		if(js.size()>0)return;
//		for (JackPot jackPot : js) {
//			dao.delete(jackPot);
//		}
		List<Chanel> chanels = chanelDao.getByName("tf1");

		int day = genDay.get(Calendar.DATE);
		while (day == genDay.get(Calendar.DATE)) {
			TVJackPot jackPot = new TVJackPot();
			jackPot.setAds(generateRandomAds(nbAds, nbPossibilities));
			jackPot.setStartDate(genDay.getTime());
			jackPot.setChanel(chanels.get(0));
			JackPotQuestion question = new JackPotQuestion();
			question.setQuestion("Dans l'épisode de Lost da la semaine dernière, qui est en réalité la fumée noire.");
			Random correctQuestion = new Random();
			int nextInt = correctQuestion.nextInt(3);
			List<Response> reponses = new ArrayList<Response>();
			String []reps = {"Jacob","Samuel","Lock"};
			for (int i = 0; i < reps.length; i++) {
				Response r = new Response();
				r.setReponse(reps[i]);
				reponses.add(r);
				if (nextInt == i)
					question.setCorrectResponse(r);
			}
			question.setReponses(reponses);
			jackPot.setQuestion(question);
			// jackPot.setValue(10.0);
			// TODO: Calculer la date de fin du test avec la durée des pubs
			dao.save(jackPot);
			genDay.add(Calendar.MINUTE, 2);
		}

	}

	@Override
	public Boolean describeAdSpace(TVBlindTest bt) throws Exception {
		long startdate = new Date().getTime() + bt.getStartIn();
		TVJackPot jackPot = new TVJackPot();
		List<AdBrandPossibiliites> res = new ArrayList<AdBrandPossibiliites>();
		TVAd[] ads = bt.getAds();
		for (TVAd tvAd : ads) {
			Ad ad = adDao.getByUid(tvAd.getAdUid());
			AdBrandPossibiliites apos = generateAdBrandPossibilities(ad, DEFAULT_POSSIBILITES);
			apos.setPauseBefore(tvAd.getPauseBefore());
			res.add(apos);
		}
		jackPot.setAds(res);
		jackPot.setStartDate(new Date(startdate));

		Chanel chanel = chanelDao.getConnectChanel(bt.getChanelId(), bt
				.getPassword());
		if (chanel == null)
			return false;

		jackPot.setChanel(chanel);
		JackPotQuestion question = new JackPotQuestion();
		question.setQuestion(bt.getQuestion().getQuestion());

		TVAnswer[] answers = bt.getQuestion().getAnswers();
		List<Response> reponses = new ArrayList<Response>();
		for (int i = 0; i < answers.length; i++) {
			Response r = new Response();
			r.setReponse(answers[i].getAnswer());
			reponses.add(r);
			if (answers[i].getAnswer().equals(
					bt.getQuestion().getCorrectAnswer().getAnswer()))
				question.setCorrectResponse(r);
		}

		question.setReponses(reponses);
		jackPot.setQuestion(question);
		dao.save(jackPot);

		return true;
	}

}
