package tech.renovus.solarec.business;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import tech.renovus.solarec.business.impl.email.EmailFile;
import tech.renovus.solarec.exceptions.CoreException;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);
	void sendSimpleHtmlMessage(String to, String subject, String text);

	void sendMessageWithAttachment(String to, String subject, String text, String fileName, String pathToAttachment, boolean asHtml) throws CoreException;
	void sendMessageWithAttachment(String to, String subject, String text, String fileName, InputStream streamSource, boolean asHtml) throws CoreException;
	void sendMessageWithAttachment(List<String> emails, List<String> emailsCC, List<String> emailsBCC, String subject, String content, Collection<EmailFile> files, boolean asHtml) throws CoreException;

}
