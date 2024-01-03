package com.proyecto.microservice.mail.sender.service;

import com.proyecto.microservice.mail.sender.domain.common.ExceptionMailSender;
import com.proyecto.microservice.mail.sender.domain.common.ResponseMailSender;
import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MailSenderServiceImpl implements IMailSenderService{

    @Value("${mail.sender.email}")
    private String mailSenderEmail;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public ResponseMailSender<MailDTO> sendMail(MailDTO mailDto) {
        try{
            this.javaMailSender.send(this.createSimpleMailMessage(mailDto));
            return ResponseMailSender.<MailDTO>builder().status(HttpStatus.OK.value()).code("OK-01").message("Mensaje enviado.").data(mailDto).build();
        }catch (ExceptionMailSender e) {
            throw new ExceptionMailSender(e.getStatus(), e.getCode(), e.getMessage());
        }catch (Exception e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-01", "Error Server internal");
        }
    }

    @Override
    public ResponseMailSender<MailDTO> sendMailWithFile(MailFileDTO mailFileDto) {
        try {
            MultipartFile multipartFile = mailFileDto.getMultipartFile();
            String fileName = multipartFile.getOriginalFilename();
            Path path = this.createFileTemp(fileName, multipartFile);
            this.javaMailSender.send(this.createMimeMessage(fileName, mailFileDto, path));
            Files.delete(path);
            return ResponseMailSender.<MailDTO>builder().status(HttpStatus.OK.value()).code("OK-02").message("Send message with file.").data(mailFileDto).build();
        }catch (ExceptionMailSender e) {
            throw new ExceptionMailSender(e.getStatus(), e.getCode(), e.getMessage());
        }catch (Exception e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-02", "Error Server internal");
        }
    }

    private Path createFileTemp(String fileName, MultipartFile multipartFile) {
        try {
            Path path = Files.createTempFile("mail_attachment_", fileName);
            Files.write(path, multipartFile.getBytes());
            return path;
        } catch (IOException e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-03", "Error created file temp");
        }
    }

    private SimpleMailMessage createSimpleMailMessage(MailDTO mailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(this.mailSenderEmail);
        mailMessage.setTo(mailDto.getToUser());
        mailMessage.setSubject(mailDto.getSubject());
        mailMessage.setText(mailDto.getMessage());
        return mailMessage;
    }

    private MimeMessage createMimeMessage(String fileName, MailFileDTO mailFileDto, Path path) {
        try {
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(this.mailSenderEmail);
            mimeMessageHelper.setTo(mailFileDto.getToUser());
            mimeMessageHelper.setSubject(mailFileDto.getSubject());
            mimeMessageHelper.setText(mailFileDto.getMessage());
            mimeMessageHelper.addAttachment(fileName, path.toFile());

            return mimeMessage;
        } catch (MessagingException e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-04", "Error created MimeMessage");
        }
    }

}
