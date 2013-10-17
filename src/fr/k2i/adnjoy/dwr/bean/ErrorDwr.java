package fr.k2i.adnjoy.dwr.bean;

public class ErrorDwr {
	
	public static final Integer SESS_EXP = 1;
	public static final Integer EXCEPTION = 2;
	public static final Integer NO_ADMIN = 3;
	public static final String SESS_EXP_STR = "Session expirée";
	public static final String NO_ADMIN_STR = "No Admin Rights";
	
	private String message;
	private Integer errno;
	private String execJs;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the errno
	 */
	public Integer getErrno() {
		return errno;
	}
	/**
	 * @param errno the errno to set
	 */
	public void setErrno(Integer errno) {
		this.errno = errno;
	}
	/**
	 * @return the execJs
	 */
	public String getExecJs() {
		return execJs;
	}
	/**
	 * @param execJs the execJs to set
	 */
	public void setExecJs(String execJs) {
		this.execJs = execJs;
	}
	
	
	
}
