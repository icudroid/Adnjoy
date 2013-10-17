package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.k2i.adnjoy.business.ad.Brand;
import fr.k2i.adnjoy.business.ad.NoShowWith;
import fr.k2i.adnjoy.dao.BrandDao;
import fr.k2i.adnjoy.dao.FileDao;
import fr.k2i.adnjoy.dao.NoShowWithDao;
import fr.k2i.adnjoy.dwr.bean.BrandBean;
import fr.k2i.adnjoy.manager.BrandManager;
import fr.k2i.adnjoy.manager.Manager;

public class BrandManagerImpl extends Manager<BrandDao, Brand, Long> implements
		BrandManager {

	private FileDao fileDao;

	private NoShowWithDao noShowWithDao;

	public void setNoShowWithDao(NoShowWithDao noShowWithDao) {
		this.noShowWithDao = noShowWithDao;
	}

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public List<BrandBean> getBrandsLike(String query) throws Exception {
		List<BrandBean> res = new ArrayList<BrandBean>();
		List<Brand> brandsLike = dao.getBrandsLike(query);
		for (Brand brand : brandsLike) {
			res.add(new BrandBean(brand, true));
		}
		return res;
	}

	@Override
	public List<BrandBean> getAllJsonObj() throws Exception {
		List<BrandBean> l = new ArrayList<BrandBean>();
		List<Brand> list = dao.getAll();
		for (Brand brand : list) {
			l.add(new BrandBean(brand, false));
		}
		return l;
	}

	@Override
	public BrandBean getBeanById(Long id) throws Exception {
		return new BrandBean(dao.getById(id), true);
	}

	@Override
	public void deletebyId(Long id) throws Exception {
		dao.delete(dao.getById(id));
	}

	@Override
	public void saveBean(BrandBean b) throws Exception {
		Brand brand = null;

		if (b.getId() == null) {
			brand = new Brand();
			brand.setName(b.getName());
		} else {
			brand = dao.getById(b.getId());
		}

		brand.setDlFile(fileDao.getById(b.getLogoId()));
		brand.setEmail(b.getEmail());
		brand.setFax(b.getFax());
		brand.setLogin(b.getLogin());
		brand.setPassword(b.getPassword());
		brand.setPhone(b.getPhone());
		dao.save(brand);

	}

	@Override
	public void addNoTogether(Long idBrand, Long idNoBrand) throws Exception {
		Brand brand = dao.getById(idBrand);
		Brand noBrand = dao.getById(idNoBrand);
		List<NoShowWith> noTogether = brand.getNoTogether();
		if (noTogether == null) {
			noTogether = new ArrayList<NoShowWith>();
			brand.setNoTogether(noTogether);
		}
		NoShowWith noShowWith = new NoShowWith();
		noShowWith.setBrandDst(noBrand);
		noShowWith.setBrandSrc(brand);
		noTogether.add(noShowWith);
		dao.save(brand);
	}

	@Override
	public void deleteNoTogetherBrand(Long idBrand, Long idNoBrand)
			throws Exception {
		Brand brand = dao.getById(idBrand);
		List<NoShowWith> noTogether = brand.getNoTogether();
		for (NoShowWith noShowWith : noTogether) {
			if (noShowWith.getBrandDst().getId().equals(idNoBrand)) {
				noTogether.remove(noShowWith);
				dao.save(brand);
				noShowWithDao.delete(noShowWith);
				break;
			}
		}
	}

	@Override
	public List<BrandBean> getNoTogetherBrands(Long idBrand) throws Exception {
		List<BrandBean> res = new ArrayList<BrandBean>();
		Brand brand = dao.getById(idBrand);
		List<NoShowWith> noTogether = brand.getNoTogether();

		for (NoShowWith noShowWith : noTogether) {
			res.add(new BrandBean(noShowWith.getBrandDst(), false));
		}
		return res;
	}

}
