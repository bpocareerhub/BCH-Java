package com.bch.bom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.bch.beans.Users;


public class EmailBom {
	private String toEmail;
	private String subject = null;
	private String host = null;
	private String body = null;
	private String hashLink = null;	
	private String url = null;
	private String from = null;
	private String partnersLink;
	private String stmp = null;
	Properties properties = System.getProperties();
	Properties prop = new Properties();
	static final Logger logger = Logger.getLogger(EmailBom.class);
		
	
	public EmailBom(Users user) {
		super();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.toEmail = user.getEmail();		
		this.hashLink = user.getHash();
		this.partnersLink = user.getBranch();
		this.subject = prop.getProperty("subject");
		this.host = prop.getProperty("host");
		this.url = prop.getProperty("url");
		this.from = prop.getProperty("from");
		this.stmp = prop.getProperty("smtp");
		PropertyConfigurator.configure("log4j.properties");
	}
	

	public boolean sendMail() {
		boolean success = false;

		properties.setProperty(this.stmp,this.host);
		
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		
		this.body = this.emailBody(this.hashLink);
		
		try {
			message.setFrom(new InternetAddress(this.from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.toEmail));
			message.setSubject(this.subject);
			message.setContent(this.body,"text/html");
			Transport.send(message);
			success = true;	
			
		} catch (AddressException e) {
			logger.error(e.getMessage());	
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
		
		return success;
	}
	
	private String emailBody(String hashLink) {
		return "<center>" +
				"<table width=\"800px\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-family: arial;\">" +
				"<tr>" +
				"<td colspan=\"4\">" +
				"<table width=\"100%\">" +
				"<tr>" +
				"<td style=\"color: gray; font-size: 20px\">" +
				"<b><a href=\"http://www.bpocareerhub.com\">" +
				"<img src=\"http://www.bpocareerhub.com/list/20120203/images/ebcl_bch_logo_left.jpg\" alt=\"BPOCareerHub.com\" style=\"display: block;\" border=\"0\"/>" +
				"</a>" +
				"</b>" +
				"</td>" +
				"<td style=\"color: #898989; font-size: 12px; text-align: right\" valign=\"bottom\">" +
				"&nbsp;" +
				"</td>" +
				"</tr>" +
				"</table> " +
				"</td>" +
				"</tr>" +
				"<tr>" +
				"<td style=\"background-color: #b7b7b7\" height=\"30\" colspan=\"4\">" +
				"</td>" +
				"</tr>" +
				"<!-- BEGIN BODY -->" +
				"<tr>" +
				"<td colspan=\"4\">" +
				"<table cellspacing=\"0\" cellpadding=\"0\">" +
				"<tr>" +
				"<td colspan=\"3\" style=\"font-size: 40px;\">" +
				"<center>" +
				"<b><a href=\"http://www.facebook.com/bpocareerhub\"><img src=\"images/ebcl_bch_img_01.jpg\" style=\"display: block;\" alt=\"BPOcareerHUB\" border=\"0\"/></a></b>" +
				"</center>" +
				"</td>" +
				"</tr>" +
				"<tr>" +
				"<td colspan=\"3\" style=\"font-size: 20px;\">" +
				"<center>" +
				"<a href=\"http://www.facebook.com/bpocareerhub\"><img src=\"images/ebcl_bch_img_02.jpg\" style=\"display: block;\" alt=\"Your Industry\" border=\"0\"/></a>" +
				"</center>" +
				"</td>" +
				"</tr>" +
				"<tr>" +
				"<td colspan=\"3\" style=\"font-size: 20px;\">" +
				"<center>" +
				"<a href=\"http://www.facebook.com/bpocareerhub\"><img src=\"images/ebcl_bch_img_03.jpg\" style=\"display: block;\" alt=\"Your Job Portal\" border=\"0\"/></a>" +
				"</center>" +
				"</td>" +
				"</tr>" +
				"<tr>" +
				"<td valign=\"top\" style=\"padding: 40px; font-size: 14px; font-family: arial; font-weight: bold; color: #464646\">" +
				"Greetings from BPO Career Hub! </br></br>" +
				"BCH partnered with AMA to bring its students better career opportunities. More jobs await YOU! Click <a href=\""+this.url+"/"+ this.partnersLink +"?hash="+hashLink+"\"><b>HERE</b></a> to see what jobs are in-store for you! " +
				"</td>" +
				"</tr>" +
				"</table>" +
				"</td>" +
				"</tr>" +
				"<!-- END BODY -->" +
				"<tr>" +
				"<td colspan=\"3\" style=\"background-image: url('images/stripe_ln.jpg'); repeat-background: repeat-x\" bgcolor=\"#B7B7B7\" height=\"30px\">" +
				"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
				"<td style=\"padding-right: 20px; padding-left: 20px\">" +
				"<a href=\"http://www.facebook.com/bpocareerhub\" style=\"font-size: 12px; text-decoration: none; color: #ffffff; font-weight: bold\">" +
				"<img src=\"images/facebook.png\" alt=\"Like us on facebook\" border=\"0\"/> Like us" +
				"</a>" +
				"</td>" +
				"<td style=\"padding-right: 20px\">" +
				"<a href=\"http://www.twitter.com/bpocareerhub\" style=\"font-size: 12px; text-decoration: none; color: #ffffff; font-weight: bold\">" +
				"<img src=\"images/twitter.png\" alt=\"Follow us on twitter\" border=\"0\"/> Follow us" +
				"</a>" +
				"</td>" +
				"<td style=\"padding-right: 20px\">" +
				"<a href=\"mailto:marketing@bpocareerhub.com\" style=\"font-size: 12px; text-decoration: none; color: #ffffff; font-weight: bold\">" +
				"<img src=\"images/email.png\" alt=\"Contact us\" border=\"0\"/> Contact us" +
				"</a>" +
				"</td>" +
				"</table>" +
				"</td>" +
				"<td style=\"background-image: url('images/stripe_ln.jpg'); repeat-background: repeat-x; font-size: 10px; text-align: right\" bgcolor=\"#B7B7B7\" height=\"30px\">" +
				"To Unsubscribe, Click [UNSUBSCRIBE] &nbsp;&nbsp;&nbsp;" +
				"</td>" +
				"</tr>" +
				"<tr>" +
				"<td colspan=\"4\" align=\"center\" style=\"font-size: 10px; padding-top: 5px;\">" +
				"3509B, 35th floor, Robinson's Equitable Tower, ADB Ave, Ortigas Center, Pasig City<br />" +
				"(+632)570-9739 / (+632)570-8759" +
				"</td>" +
				"</tr>" +
				"</table>" +
				"</center>";
		
	}
}
