package fr.k2i.adnjoy.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.k2i.adnjoy.business.ad.Type;
import fr.k2i.adnjoy.dao.TypeDao;
import fr.k2i.adnjoy.manager.Manager;
import fr.k2i.adnjoy.manager.TypeManager;
import fr.k2i.adnjoy.stripes.bean.TypeBean;

public class TypeManagerImpl extends Manager<TypeDao, Type, Long> implements TypeManager {

	@Override
	public List<TypeBean> getAllBean() throws Exception {
		List<TypeBean> res = new ArrayList<TypeBean>();
		List<Type> all = dao.getAll();
		for (Type type : all) {
			res.add(new TypeBean(type));
		}
		return res;
	}


}
