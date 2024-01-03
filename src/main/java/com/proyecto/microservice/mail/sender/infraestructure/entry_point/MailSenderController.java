package com.proyecto.microservice.mail.sender.infraestructure.entry_point;


import com.proyecto.microservice.mail.sender.domain.common.ResponseMailSender;
import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;
import com.proyecto.microservice.mail.sender.service.IMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail-sender")
public class MailSenderController {

    @Autowired
    private IMailSenderService iMailSenderService;

    @PostMapping(value = "/send-message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMailSender<MailDTO> sendMessage(@RequestBody MailDTO mailDto){
        return this.iMailSenderService.sendMail(mailDto);
    }

    @PostMapping(value = "/send-message-with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseMailSender<MailDTO> sendMessage(@ModelAttribute MailFileDTO mailFileDTO) {
        return this.iMailSenderService.sendMailWithFile(mailFileDTO);
    }



}
