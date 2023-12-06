package tech.renovus.solarec.business.impl.email;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;

/**
 * @source https://www.baeldung.com/spring-email
 * @source https://howtodoinjava.com/spring-boot2/send-email-with-attachment/
 */

@Component
public class EmailServiceImpl implements EmailService {

	// --- Properties ----------------------------
	@Autowired private JavaMailSender emailSender;
	@Autowired private RenovusSolarecConfiguration config;

	private static final String PROTOCOL = "imap";
    private static final String HOST = "imap.gmail.com";
    protected static final String USERNAME = "pferrari@gmail.com";
    protected static final String PASSWORD = "rjmawmbjnlsqrgzj";
	
	private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.imap.socketFactory.fallback", "false");
        props.put("mail.imap.socketFactory.port", "993");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.host", HOST);
        props.put("mail.imap.user", USERNAME);
        props.put("mail.imap.protocol", PROTOCOL);
        return props;
    }
	
	// --- Overridden methods --------------------
	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(this.config.getMailSendFrom());
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
	
	@Override
	public void sendSimpleHtmlMessage(String to, String subject, String html) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
			helper.setFrom(this.config.getMailSendFrom());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(html, true);
			emailSender.send(message);
		} catch (MessagingException ex) {
			/* */
		}
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String fileName, String pathToAttachment, boolean asHtml) throws CoreException {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
	
			helper.setFrom(this.config.getMailSendFrom());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, asHtml);
	
			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment(fileName, file);
	
			emailSender.send(message);
		} catch (MessagingException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public void sendMessageWithAttachment(List<String> emails, List<String> emailsCc, List<String> emailsBcc, String subject, String content, Collection<EmailFile> files, boolean asHtml) throws CoreException {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
	
			helper.setFrom(this.config.getMailSendFrom());
			if (CollectionUtil.notEmpty(emails)) helper.setTo(emails.toArray(new String[0]));
			if (CollectionUtil.notEmpty(emailsCc)) helper.setCc(emailsCc.toArray(new String[0]));
			if (CollectionUtil.notEmpty(emailsBcc)) helper.setBcc(emailsBcc.toArray(new String[0]));
			helper.setSubject(subject);
			helper.setText(content, asHtml);
	
			for (EmailFile emailFile : files) {
				helper.addAttachment(emailFile.getName(), new FileSystemResource(new File(emailFile.getPath())));
			}
	
			emailSender.send(message);
		} catch (MessagingException e) {
			throw new CoreException(e);
		}
	}
	
	@Override
	public void checkMail() throws CoreException {

		try {
			// Creates a javax.mail.Authenticator object.
			Authenticator auth = new Authenticator() {
				@Override protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USERNAME, PASSWORD);
				}
			};
	
	        // Creating mail session.
	        Session session = Session.getDefaultInstance(getProperties(), auth);
	
	        // Get the store provider and connect to the store.
	        Store store = session.getStore(PROTOCOL);
	        store.connect(HOST, USERNAME, PASSWORD);
	
	        // Get folder and open the INBOX folder in the store.
	        Calendar cal = GregorianCalendar.getInstance();
	        cal.setTime(new Date());
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        cal.set(Calendar.AM_PM, Calendar.AM);
	        cal.add(Calendar.DAY_OF_MONTH, -1);
	        
	        ReceivedDateTerm       minDateTerm = new ReceivedDateTerm(ComparisonTerm.GT, cal.getTime());

	        Folder inbox = store.getFolder("INBOX");
	        inbox.open(Folder.READ_ONLY);
	
	        // Retrieve the messages from the folder.
	        Message[] messages = inbox.search(minDateTerm);
	        for (Message message : messages) {
	            //message.setFlag(Flags.Flag.SEEN, true);  // todo: put back
	            if (message != null) {
	                Date sentDate = message.getSentDate();
	                Address[] from = message.getFrom();
	                Address[] recipients = message.getAllRecipients();
	                String subject = message.getSubject();
	                System.out.println("#: " + message.getMessageNumber());
	                System.out.println("Sent: " + DateUtil.formatDateTime(sentDate, DateUtil.FMT_MILITAR));
	                System.out.println("Subject: " + subject);
	                System.out.print("From: ");
	                if (from != null) for (Address add : from) System.out.print(add.toString() + " ");
	                System.out.println();
	                System.out.println("Recipients: ");
	                if (recipients != null) for (Address add : recipients) System.out.print(add.toString() + " ");
	                System.out.println();
	                System.out.println();
	            }
	        }
	
	        inbox.close(true);
	        store.close();
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}
