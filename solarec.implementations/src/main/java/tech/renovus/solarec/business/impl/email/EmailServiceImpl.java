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
}
