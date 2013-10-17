package fr.k2i.adnjoy.service;

public interface SendMailService {
	void sendMail(String subject, String message,String email)throws Exception;
}
