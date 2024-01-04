package com.proyecto.microservice.mail.sender.domain.service;

import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;

public interface IMailSenderService {
    void sendMail(MailDTO mailDto);
    void sendMailWithFile(MailFileDTO mailFileDto);
}
