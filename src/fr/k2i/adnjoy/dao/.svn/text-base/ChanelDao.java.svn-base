package fr.k2i.adnjoy.dao;

import java.util.List;

import fr.k2i.adnjoy.business.ad.Chanel;

public interface ChanelDao extends IBaseDao<Chanel, Long>{
	List<Chanel> getAllFromCountry(Long idCountry, String filter)throws Exception;
	List<Chanel> getByName(String startName)throws Exception;
	Chanel getConnectChanel(String chanelId, String password)throws Exception;
}
