package net.kikkirej.stundenplanmailer.model;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import net.kikkirej.stundenplanmailer.PropertiesSingleton;


public class StundenPlanMail {
	
	Logger logger = Logger.getLogger(StundenPlanMail.class);

	@SuppressWarnings("unused")
	private Tag tag;

	public StundenPlanMail(Tag tag) {
		
		PropertiesSingleton propertiesSingleton = PropertiesSingleton.getInstance();
		
		String mailHost = propertiesSingleton.getProperty("mailHost");
		int smtpPort = Integer.parseInt(propertiesSingleton.getProperty("smtpPort"));
		String user = propertiesSingleton.getProperty("user");
		String password = propertiesSingleton.getProperty("password");
		boolean sslOnConnect = Boolean.parseBoolean(propertiesSingleton.getProperty("sslOnConnect"));
		boolean sslCheckServerIdentity = Boolean.parseBoolean(propertiesSingleton.getProperty("sslCheckServerIdentity"));
		String emailFrom = propertiesSingleton.getProperty("emailFrom");
		String emailSubject = propertiesSingleton.getProperty("emailSubject");
		String emailRecipientOne = propertiesSingleton.getProperty("emailRecipientOne");
		String emailRecipientTwo = propertiesSingleton.getProperty("emailRecipientTwo");
		boolean startTLSEnabled = Boolean.parseBoolean(propertiesSingleton.getProperty("startTLSEnabled"));
		
//		logger.debug("Mailhost: " + mailHost);
//		logger.debug("SMTP Port: " + smtpPort);
//		logger.debug("User + Password: " + user + " " + password);
//		logger.debug("SSL status: " + sslOnConnect);
//		logger.debug("Ceck server identity status: " + sslCheckServerIdentity);
//		logger.debug("Email from: " + emailFrom);
//		logger.debug("Email subject" + emailSubject);
//		logger.debug("Message: " + tag.toString());
//		logger.debug("Recipients are: " + emailRecipientOne + " " + emailRecipientTwo);
//		logger.debug("Start TLS Status: " + startTLSEnabled);
		
		try {
			
			this.tag = tag;
			Email email = new SimpleEmail();
			email.setHostName(mailHost);
			email.setSmtpPort(smtpPort);
			email.setAuthenticator(new DefaultAuthenticator(user, password));
			email.setSSLOnConnect(sslOnConnect);
			email.setSSLCheckServerIdentity(sslCheckServerIdentity);
			email.setFrom(emailFrom);
			email.setSubject(emailSubject);
			email.setMsg(tag.toString());
			email.addTo(emailRecipientOne);
			if(emailRecipientTwo != null && !emailRecipientTwo.equals("")){
				email.addTo(emailRecipientTwo);
			}
			email.setStartTLSEnabled(startTLSEnabled);
			email.send();
			
			logger.info("Mail erfolgreich gesendet.");
			
	} catch (Exception e) {
		
		logger.error("Beim senden der Mail ist ein Fehler aufgetreten.",e);
		}
	}
}
