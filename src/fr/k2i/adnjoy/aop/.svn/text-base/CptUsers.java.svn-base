package fr.k2i.adnjoy.aop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.k2i.adnjoy.dwr.bean.ScoreBean;

public class CptUsers {
	private Long cpt = 0L;
	private Map<Long,ScoreBean>usersIdScore = new HashMap<Long,ScoreBean>();
	
	public Long getCpt() {
		return cpt;
	}
	public void setCpt(Long cpt) {
		this.cpt = cpt;
	}
	public List<Long> getUsersId() {
		return new ArrayList<Long>(usersIdScore.keySet());
	}
	
	public void setUserScore(Long idUser,ScoreBean score){
		usersIdScore.put(idUser,score);
	}
	public long incrementCpt() {
		return ++cpt;
	}
	
	public List<ScoreBean> getScores() {
		 List<ScoreBean> res = new ArrayList<ScoreBean>();
		 Collection<ScoreBean> values = usersIdScore.values();
		 for (ScoreBean scoreBean : values) {
			if(scoreBean!=null)res.add(scoreBean);
		}
		return res;
	}
	public void addUser(Long id) {
		usersIdScore.put(id,null);
		
	}
	
}
