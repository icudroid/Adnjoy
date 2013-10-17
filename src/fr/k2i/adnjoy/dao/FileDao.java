package fr.k2i.adnjoy.dao;

import fr.k2i.adnjoy.business.File;

public interface FileDao extends IBaseDao<File, Long>{
	File getByUrl(String url)throws Exception;
}
