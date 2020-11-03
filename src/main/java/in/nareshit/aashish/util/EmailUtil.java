package in.nareshit.aashish.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender sender;
	
	public boolean sendEmail(
			String to,
			String subject,
			String text,
			String[] cc,
			String[] bcc,
			MultipartFile file
			) {
		boolean flag = false;
		
		try {
			flag = true;
			//1. create empty message or MIME-Multipurpose Internet Mail Extension
			MimeMessage message = sender.createMimeMessage();
			
			//2. use helper class object to set the details to the message
			/*
			 * MimeMessageHelper(MimeMessage mimeMessage, boolean multipart)
			 * This constructor accepts two values in parenthesis, message and 
			 * boolean type, if boolean type we take as true that means attachment
			 * file is available and if we take it as false that means attachment
			 * file is not available.
			 */
			MimeMessageHelper helper = new MimeMessageHelper(message, file!=null?true:false);
			
			//3. set the details to the message
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
			if(cc!=null)
				helper.setCc(cc);
			if(bcc!=null)
				helper.setBcc(bcc);
			
			if(file!=null) {
				helper.addAttachment(file.getOriginalFilename(), file);
			}
			
			//3. finally send the message
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	// overloaded methods
	public boolean sendEmail(
			String to,
			String subject,
			String text
			) {
		
		return sendEmail(to, subject, text, null, null, null); 
	}
}
