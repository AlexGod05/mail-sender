package com.proyecto.microservice.mail.sender.domain.mapper;

import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@Component
public class MailDtoMapper {

    public SimpleMailMessage createSimpleMailMessage(String userName, MailDTO mailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(userName);
        mailMessage.setTo(mailDto.getToUser());
        mailMessage.setSubject(mailDto.getSubject());
        mailMessage.setText(mailDto.getMessage());
        return mailMessage;
    }

    public MimeMessage createMimeMessage(MimeMessage mimeMessage, String userName,MailFileDTO mailFileDto, String fileName, Path path) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(userName);
        mimeMessageHelper.setTo(mailFileDto.getToUser());
        mimeMessageHelper.setSubject(mailFileDto.getSubject());
        mimeMessageHelper.setText(mailFileDto.getMessage());
        mimeMessageHelper.addAttachment(fileName, path.toFile());

        return mimeMessage;

    }
}
