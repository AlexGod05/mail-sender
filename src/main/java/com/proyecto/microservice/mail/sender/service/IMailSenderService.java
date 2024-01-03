package com.proyecto.microservice.mail.sender.service;

import com.proyecto.microservice.mail.sender.domain.common.ResponseMailSender;
import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;

public interface IMailSenderService {

    ResponseMailSender<MailDTO> sendMail(MailDTO mailDto);
    ResponseMailSender<MailDTO> sendMailWithFile(MailFileDTO mailFileDTO);
}
