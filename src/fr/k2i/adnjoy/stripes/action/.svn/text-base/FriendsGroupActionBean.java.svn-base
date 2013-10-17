/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package fr.k2i.adnjoy.stripes.action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.json.JSONObject;

import fr.k2i.adnjoy.aop.AopChat;
import fr.k2i.adnjoy.aop.AopPlayGame;
import fr.k2i.adnjoy.aop.ChatEvent;
import fr.k2i.adnjoy.aop.ChatEventListener;
import fr.k2i.adnjoy.aop.CptUsers;
import fr.k2i.adnjoy.aop.CptUsersMessage;
import fr.k2i.adnjoy.aop.PlayEvent;
import fr.k2i.adnjoy.aop.PlayEventListener;
import fr.k2i.adnjoy.business.user.User;
import fr.k2i.adnjoy.dwr.bean.ExtResponseBean;
import fr.k2i.adnjoy.dwr.bean.ScoreBean;
import fr.k2i.adnjoy.manager.FriendsGroupManager;
import fr.k2i.adnjoy.manager.UserManager;
import fr.k2i.adnjoy.stripes.bean.FriendBean;
import fr.k2i.adnjoy.stripes.bean.UserMessageBean;

@UrlBinding("/FriendsGroup.htm")
public class FriendsGroupActionBean extends BaseActionBean {
	
	private long idBlindTest;
	private long idJackPot;
	private long idUser;
	private long version;
	private String emailFriend;
	private String pseudoFilter;
	private Integer start;
	private Integer limit;
	private String message;

	@SpringBean
	private AopChat listenerChat;

	@SpringBean
	private AopPlayGame listenerPlayGame;
	
	@SpringBean
	private UserManager userManager;

	@SpringBean
	private FriendsGroupManager friendsGroupManager;
	
	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getEmailFriend() {
		return emailFriend;
	}

	public void setEmailFriend(String emailFriend) {
		this.emailFriend = emailFriend;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getPseudoFilter() {
		return pseudoFilter;
	}

	public void setPseudoFilter(String pseudoFilter) {
		this.pseudoFilter = pseudoFilter;
	}

	public long getIdJackPot() {
		return idJackPot;
	}

	public void setIdJackPot(long idJackPot) {
		this.idJackPot = idJackPot;
	}
	
	public long getIdBlindTest() {
		return idBlindTest;
	}

	public void setIdBlindTest(long idBlindTest) {
		this.idBlindTest = idBlindTest;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/friendsGroup.jsp");
    }
	
	@HandlesEvent("listFriends")
    public Resolution listFriends() {
		ExtResponseBean res = null;
		try {
			User user = userManager.getUserFromContext(this.getContext());
			List<FriendBean> friends = friendsGroupManager.getAll(user.getId(),pseudoFilter);
			res = new ExtResponseBean();
			res.setResults(friends.size());
			res.setRows(friends);
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new StreamingResolution("text",new JSONObject(res).toString());
    }

	@HandlesEvent("addFriend")
    public Resolution addFriend() {
		try {
			User user = userManager.getUserFromContext(this.getContext());
			friendsGroupManager.addFriend(user.getId(), idUser);
		}catch (Exception e) {
			return new StreamingResolution("text","{success:false}");
		}
		return new StreamingResolution("text","{success:true}");
    }
	
	@HandlesEvent("deleteFriend")
    public Resolution deleteFriend() {
		try {
			User user = userManager.getUserFromContext(this.getContext());
			friendsGroupManager.deleteFriend(user.getId(), idUser);
		}catch (Exception e) {
			return new StreamingResolution("text","{success:false}");
		}
		return new StreamingResolution("text","{success:true}");
    }
	
	@HandlesEvent("invitFriend")
    public Resolution invitFriend() {
		try {
			User user = userManager.getUserFromContext(this.getContext());
			friendsGroupManager.invitFriend(user.getId(), emailFriend);
		}catch (Exception e) {
			return new StreamingResolution("text","{success:false}");
		}
		return new StreamingResolution("text","{success:true}");
    }
	
	@HandlesEvent("validateFriend")
    public Resolution validateFriend() {
		try {
			User user = userManager.getUserFromContext(this.getContext());
			friendsGroupManager.friendValidate(user.getId(), idUser);
		}catch (Exception e) {
			return new StreamingResolution("text","{success:false}");
		}
		return new StreamingResolution("text","{success:true}");
    }
	
	@HandlesEvent("listUserByPseudo")
    public Resolution listUserByPseudo() {
		ExtResponseBean res = null;
		try {
			List<FriendBean> users= userManager.getUsersByPseudo(pseudoFilter,start,limit);
			int countUserFilter= userManager.getCountUsersByPseudo(pseudoFilter);
			res = new ExtResponseBean();
			res.setResults(countUserFilter);
			res.setRows(users);
			res.setSuccess(true);
		}catch (Exception e) {
			return new StreamingResolution("text","{success:false}");
		}
		return new StreamingResolution("text",new JSONObject(res).toString());
    }
	
	@HandlesEvent("getFriendsToValidate")
    public Resolution getFriendsToValidate() {
		ExtResponseBean res = null;
		try {
			User user = userManager.getUserFromContext(this.getContext());
			List<FriendBean> users = friendsGroupManager.getNoValidateFriends(user.getId());
			res = new ExtResponseBean();
			res.setResults(users.size());
			res.setRows(users);
			res.setSuccess(true);
		}catch (Exception e) {
			return new StreamingResolution("text","{success:false}");
		}
		return new StreamingResolution("text",new JSONObject(res).toString());
    }
	
	@HandlesEvent("getScoresFriends")
    public Resolution getScoresFriends() {
		ExtResponseBean res =  new ExtResponseBean();
		final EventOk eventOK = new EventOk();
			final User user;
			try {
				user = userManager.getUserFromContext(this.getContext());
			} catch (Exception e1) {
				return new StreamingResolution("text","{success:false}");
			}
			
			boolean uptodate = listenerPlayGame.needUpdate(idJackPot,user.getId(),version);
			if(uptodate){
				PlayEventListener playEventListener = new PlayEventListener(idJackPot,user.getId()) {
					@Override
					public void played(PlayEvent evt) {
						listenerPlayGame.removePlayEventListener(this);
						eventOK.setData(evt.getCptUsers());
						eventOK.setOk(true);
					}
				};
				listenerPlayGame.addPlayEventListener(playEventListener);
				
				Thread t = new Thread(){
					@Override
					public void run() {
						while(!eventOK.isOk()){
							try {
								 Thread.sleep (100) ;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println("End Thread");
					}
				};
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				CptUsers data = (CptUsers) eventOK.getData();
				res.setVersion(data.getCpt());				
//				List<ScoreBean> users = friendsGroupManager.getScoresFriends(user.getId(),idJackPot);
				res.setResults(data.getUsersId().size());
				res.setRows(data.getScores());
				res.setSuccess(true);
			}catch (Exception e) {
				res.setSuccess(false);
			}
		return new StreamingResolution("text",new JSONObject(res).toString());
    }
	
	@HandlesEvent("chatFriends")
    public Resolution chatFriends() {
		ExtResponseBean res =  new ExtResponseBean();
		final EventOk eventOK = new EventOk();
			final User user;
			try {
				user = userManager.getUserFromContext(this.getContext());
			} catch (Exception e1) {
				return new StreamingResolution("text","{success:false}");
			}
			
			boolean uptodate = listenerChat.needUpdate(user.getId(),version);
			if(uptodate){
				ChatEventListener chatEventListener = new ChatEventListener(user.getId()) {
					@Override
					public void messaged(ChatEvent evt) {
						listenerChat.removeChatEventListener(this);
						eventOK.setOk(true);
					}
				};
				listenerChat.addChatEventListener(chatEventListener);
				
				Thread t = new Thread(){
					@Override
					public void run() {
						while(!eventOK.isOk()){
							try {
								 Thread.sleep (100) ;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println("End Thread");
					}
				};
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				CptUsersMessage cptUsersMessage = listenerChat.getCptUsersMessage(user.getId());
//				CptUsersMessage cptUsersMessage = (CptUsersMessage) eventOK.getData();
				List<UserMessageBean> messages = null;
				if(cptUsersMessage == null){
					messages = new ArrayList<UserMessageBean>();
					res.setVersion(version);
				}else{
					messages = cptUsersMessage.getMessages(version);
					res.setVersion(cptUsersMessage.getCpt());
				}
				
				res.setResults(messages.size());
				res.setRows(messages);
				
				res.setSuccess(true);
			}catch (Exception e) {
				res.setSuccess(false);
			}
		return new StreamingResolution("text",new JSONObject(res).toString());
    }
	
	@HandlesEvent("registerChat")
    public Resolution registerChat() {
		try {
			userManager.registerChat(listenerChat,this.getContext());
		} catch (Exception e1) {
			return new StreamingResolution("text","{success:false}");
		}
		return new StreamingResolution("text","{success:true}");
	}
	
	@HandlesEvent("postMessage")
    public Resolution postMessage() {
		try {
			User user = userManager.getUserFromContext(this.getContext());
			UserMessageBean msg = listenerChat.postMessage(user.getId(),message);
			return new StreamingResolution("text",new JSONObject(msg).toString());
		} catch (Exception e1) {
			return new StreamingResolution("text","{success:false}");
		}
    }
	
}
class EventOk{
	private boolean ok = false;
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
}