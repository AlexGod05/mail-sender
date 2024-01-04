package com.proyecto.microservice.mail.sender.domain.usecase;

import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;

public interface IMailSenderUseCase {
    void sendMail(MailDTO mailDto);
    void sendMailWithFile(MailFileDTO mailFileDto);

}
