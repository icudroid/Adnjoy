package fr.k2i.adnjoy.manager.impl;

import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.dao.FileDao;
import fr.k2i.adnjoy.manager.FileManager;
import fr.k2i.adnjoy.manager.Manager;

public class FileManagerImpl extends Manager<FileDao, File, Long> implements FileManager {

	@Override
	public File getByUrl(String url) throws Exception {
		return dao.getByUrl(url);
	}

	

}
