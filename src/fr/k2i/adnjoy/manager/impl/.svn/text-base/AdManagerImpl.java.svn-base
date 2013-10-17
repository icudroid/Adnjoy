package fr.k2i.adnjoy.manager.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.k2i.adnjoy.business.ad.Ad;
import fr.k2i.adnjoy.business.ad.Brand;
import fr.k2i.adnjoy.dao.AdDao;
import fr.k2i.adnjoy.dao.BrandDao;
import fr.k2i.adnjoy.dao.FileDao;
import fr.k2i.adnjoy.dao.TypeDao;
import fr.k2i.adnjoy.dwr.bean.AdBean;
import fr.k2i.adnjoy.manager.AdManager;
import fr.k2i.adnjoy.manager.Manager;

public class AdManagerImpl extends Manager<AdDao, Ad, Long> implements AdManager {
	//private static final String VIDEO_DIRECTORY = "h:/AdVideo/";
	private static String VIDEO_DIRECTORY;
	private static String DIRECTORY;
	private BrandDao brandDao;
	private FileDao fileDao;
	private TypeDao typeDao;

	
	public AdManagerImpl(String dir,String videodir ){
		DIRECTORY = dir;
		VIDEO_DIRECTORY = videodir;
	}
	
	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public void saveFromUI(AdBean ad) throws Exception {
		Ad adToSave = null;
		if(ad.getIdAd()!=null){
			adToSave = dao.getById(ad.getIdAd());
		}else{
			 adToSave = new Ad();
		}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//adToSave.setId(ad.getIdAd());
			
			if(adToSave.getId()==null){
				adToSave.setBrand(brandDao.getById(ad.getBrandId()));
			}
			fr.k2i.adnjoy.business.File tmpFileDl = fileDao.getByUrl(ad.getDlFile());
			File ftmp = new File(tmpFileDl.getFile());
			String dest = DIRECTORY+VIDEO_DIRECTORY+Long.toHexString(new Date().getTime())+tmpFileDl.getFilename();
			ftmp.renameTo(new File(dest));
			tmpFileDl.setFile(dest);
			
			if(adToSave.getDlFile()!=null){
				fileDao.delete(adToSave.getDlFile());
				adToSave.setDlFile(null);
			}
			
			adToSave.setDlFile(tmpFileDl);
			
			adToSave.setType(typeDao.getById(ad.getTypeId()));
			adToSave.setDuration(ad.getDuration());
			adToSave.setLogoText(ad.getLogoText());
			adToSave.setStartDate(sdf.parse(ad.getStartDate()));
			adToSave.setEndDate(sdf.parse(ad.getEndDate()));
			adToSave.setuId(ad.getUid());
			
		dao.save(adToSave);
	}

	@Override
	public List<AdBean> getAdsByBrand(Long brandId) throws Exception {
		List<AdBean> res = new ArrayList<AdBean>();
		List<Ad> ads = dao.getAdsByBrand(brandId);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Ad ad : ads) {
			AdBean adb = new AdBean();
			adb.setBrandId(brandId);
			if(ad.getDlFile()!=null)
			adb.setDlFile(ad.getDlFile().getDlUrl());
			adb.setDuration(ad.getDuration());
			adb.setIdAd(ad.getId());
			adb.setLogoText(ad.getLogoText());
			if(ad.getType()!=null)
			adb.setTypeId(ad.getType().getId());
			if(ad.getStartDate()!=null)
			adb.setStartDate(sdf.format(ad.getStartDate()));
			if(ad.getEndDate()!=null)
			adb.setEndDate(sdf.format(ad.getEndDate()));
			adb.setUid(ad.getuId());
			res.add(adb);
		}
		return res;
	}

	@Override
	public List<AdBean> getNewAds() throws Exception {
		List<AdBean> res = new ArrayList<AdBean>();
		List<Ad> ads = dao.getNewAds();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Ad ad : ads) {
			AdBean adb = new AdBean();
			Brand brand = ad.getBrand();
			adb.setBrandId(brand.getId());
			adb.setBrandLogoFile(brand.getDlFile().getDlUrl());
			adb.setDlFile(ad.getDlFile().getDlUrl());
			adb.setDuration(ad.getDuration());
			adb.setIdAd(ad.getId());
			adb.setLogoText(ad.getLogoText());
			adb.setTypeId(ad.getType().getId());
			if(ad.getStartDate()!=null)
			adb.setStartDate(sdf.format(ad.getStartDate()));
			if(ad.getEndDate()!=null)
			adb.setEndDate(sdf.format(ad.getEndDate()));
			adb.setUid(ad.getuId());
			res.add(adb);
		}
		return res;
	}

	@Override
	public List<fr.k2i.adnjoy.stripes.bean.AdBean> getAllBean(Long brandId)
			throws Exception {
		List<fr.k2i.adnjoy.stripes.bean.AdBean> res = new ArrayList<fr.k2i.adnjoy.stripes.bean.AdBean>();
		List<Ad> all = dao.getAdsByBrand(brandId);
		for (Ad ad : all) {
			res.add(new fr.k2i.adnjoy.stripes.bean.AdBean(ad));
		}
		return res;
	}

	@Override
	public void deletebyId(Long id) throws Exception {
		dao.delete(id);
	}

	@Override
	public fr.k2i.adnjoy.stripes.bean.AdBean getBeanById(Long id)
			throws Exception {
		return new fr.k2i.adnjoy.stripes.bean.AdBean(dao.getById(id));
	}

	@Override
	public void saveBean(fr.k2i.adnjoy.stripes.bean.AdBean a) throws Exception {
		Ad ad = null;
		if(a.getId() == null){
			ad = new Ad();
			ad.setBrand(brandDao.getById(a.getBrand().getId()));
		}else{
			ad = dao.getById(a.getId());
		}
		
		if(a.getDlFile() != null){
			ad.setDlFile(fileDao.getById(a.getDlFile().getId()));
			ad.setDuration(a.getDuration());
		}
		ad.setEndDate(a.getEndDate());
		ad.setLogoText(a.getLogoText());
		ad.setStartDate(a.getStartDate());
		ad.setuId(a.getUniqueId());
		ad.setType(typeDao.getById(a.getType().getId()));
	}

}
