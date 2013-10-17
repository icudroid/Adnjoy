package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.k2i.adnjoy.business.object.StockObject;
import fr.k2i.adnjoy.dao.BrandDao;
import fr.k2i.adnjoy.dao.FileDao;
import fr.k2i.adnjoy.dao.StockObjectDao;
import fr.k2i.adnjoy.dwr.bean.StockObjectBean;
import fr.k2i.adnjoy.manager.Manager;
import fr.k2i.adnjoy.manager.StockObjectManager;

public class StockObjectManagerImpl extends Manager<StockObjectDao, StockObject, Long> implements StockObjectManager {

	private BrandDao brandDao;
	private FileDao fileDao;
	
	public BrandDao getBrandDao() {
		return brandDao;
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public FileDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public List<StockObjectBean> getByBrandId(Long brandId) throws Exception {
		List<StockObjectBean> res = new ArrayList<StockObjectBean>();
		List<StockObject> l = dao.getByBrandId(brandId);
		for (StockObject so : l) {
			res.add(new StockObjectBean(so));
		}
		return res;
	}

	@Override
	public StockObjectBean getBeanById(Long idStockObject) throws Exception {
		return new StockObjectBean(dao.getById(idStockObject));
	}

	@Override
	public void saveBean(StockObjectBean so, Integer addNb) throws Exception {
		StockObject s = null;
		if(so.getId() == null){
			//new
			s = new StockObject();
			s.setAvailable(so.getAvailable());
			
		}else{
			//update
			s = dao.getById(so.getId());
			Long a = s.getAvailable()+addNb;
			s.setAvailable(a);
		}
		if(so.getBrandId()!=null)
			s.setBrand(brandDao.getById(so.getBrandId()));
		s.setValue(so.getValue());
		s.setName(so.getName());
		if(so.getDlFile()!=null && so.getDlFile().getId()!=null)
			s.setDlFile(fileDao.getById(so.getDlFile().getId()));
		s.setDescription(so.getDescription());

		dao.save(s);
	}

	@Override
	public void deletebyId(Long id) throws Exception {
		dao.delete(dao.getById(id));
	}
}
