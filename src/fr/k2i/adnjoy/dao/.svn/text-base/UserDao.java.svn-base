package fr.k2i.adnjoy.dao;

import java.util.List;

import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;
import fr.k2i.adnjoy.business.user.User;

public interface UserDao extends IBaseDao<User, Long>{
	public User getByLogin(String login)throws Exception;

	public User getByEmail(String email)throws Exception;

	public User getByPseudo(String pseudo)throws Exception;

	public User getByValidateNumber(String number)throws Exception;

	public List<WonJackPot> getWonJackPot(long idUser, int start, int limit)throws Exception;

	public List<User> getSimuationUser()throws Exception;

	public Integer getCountUsersByPseudo(String pseudoFilter)throws Exception;

	public List<User> filterByPseudo(String pseudoFilter, int start, int limit)throws Exception;

}
