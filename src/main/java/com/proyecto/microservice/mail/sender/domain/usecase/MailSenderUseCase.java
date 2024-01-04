package com.proyecto.microservice.mail.sender.domain.usecase;

import com.proyecto.microservice.mail.sender.domain.common.ExceptionMailSender;
import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;
import com.proyecto.microservice.mail.sender.domain.mapper.MailDtoMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
@Component
public class MailSenderUseCase implements  IMailSenderUseCase{

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailDtoMapper mailDtoMapper;


    //#1: CASO DE USO
    public void sendMail(MailDTO mailDto) {
        this.javaMailSender.send(this.createSimpleMailMessage(mailDto));
    }

    private SimpleMailMessage createSimpleMailMessage(MailDTO mailDto) {
        return this.mailDtoMapper.createSimpleMailMessage(this.javaMailSenderImpl.getUsername(), mailDto);
    }

    //#2: CASO DE USO
    public void sendMailWithFile(MailFileDTO mailFileDto) {
        MultipartFile multipartFile = mailFileDto.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();

        Path path = this.createFileTemp(fileName, multipartFile);
        this.javaMailSender.send(this.createMimeMessage(fileName, mailFileDto, path));
        this.deleteFileTemp(path);
    }

    private Path createFileTemp(String fileName, MultipartFile multipartFile) {
        try {
            Path path = Files.createTempFile("mail_attachment_", fileName);
            Files.write(path, multipartFile.getBytes());
            return path;
        } catch (IOException e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-03", "Error Created File Temp");
        }
    }

    private MimeMessage createMimeMessage(String fileName, MailFileDTO mailFileDto, Path path) {
        try {
            return this.mailDtoMapper.createMimeMessage(this.javaMailSender.createMimeMessage(), this.javaMailSenderImpl.getUsername(), mailFileDto, fileName, path);
        } catch (MessagingException e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-04", "Error created MimeMessage");
        }
    }

    private void deleteFileTemp(Path path) {
        try {
            Files.delete(path);
        }catch (Exception e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-02", "Error Delete File Temp");
        }
    }

}
