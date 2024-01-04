package com.proyecto.microservice.mail.sender.domain.service;

import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;
import com.proyecto.microservice.mail.sender.domain.usecase.IMailSenderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements IMailSenderService{

    private final IMailSenderUseCase iMailSenderUseCase;

    @Override
    public void sendMail(MailDTO mailDto) {
        this.iMailSenderUseCase.sendMail(mailDto);
    }

    @Override
    public void sendMailWithFile(MailFileDTO mailFileDto) {
        this.iMailSenderUseCase.sendMailWithFile(mailFileDto);
    }

}
