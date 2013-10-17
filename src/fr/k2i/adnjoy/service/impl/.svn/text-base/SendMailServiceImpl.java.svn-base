package fr.k2i.adnjoy.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import fr.k2i.adnjoy.service.SendMailService;

public class SendMailServiceImpl implements SendMailService {

	private String from = "contact@adnjoy.fr";
	private String mailhost = "mail.adnjoy.fr";
	
	public SendMailServiceImpl(String from,String mailhost){
		this.from = from;
		this.mailhost = mailhost;
	}
	
	public void sendMail(String subject, String message, String email)
			throws Exception {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", mailhost);

		// Get a Session object
		Session session = Session.getInstance(props, null);

		// construct the message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
		msg.setSubject(subject);
		constructMessage(msg,message);
		msg.setSentDate(new Date());

		Transport.send(msg);
	}

	private void constructMessage(Message msg,String message) throws MessagingException, IOException{
		String subject = msg.getSubject();
		StringBuffer sb = new StringBuffer();
		sb.append("<HTML>\n");
		sb.append("<HEAD>\n");
		sb.append("<TITLE>\n");
		sb.append(subject + "\n");
		sb.append("</TITLE>\n");
		sb.append("</HEAD>\n");
		sb.append("<BODY>\n");
		sb.append(message);
		sb.append("</BODY>\n");
		sb.append("</HTML>\n");

		msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));
	}

}
