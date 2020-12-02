package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.exceptions.SpringEuroaviaException;
import com.ionii.euroaviabucuresti.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmail notificationEmail) throws SpringEuroaviaException {
        MimeMessagePreparator messagePreparator=mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("andrei.ionita@euroavia-bucuresti.ro");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try{
            mailSender.send(messagePreparator);
            log.info("Activation email sent!");
        }catch (MailException e){
            log.error("Exception occured when sending mail",e);
            throw new SpringEuroaviaException("Exception occured when sending mail to"+notificationEmail.getRecipient(),e);
        }
    }
}
