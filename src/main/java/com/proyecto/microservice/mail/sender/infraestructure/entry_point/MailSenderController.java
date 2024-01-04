package com.proyecto.microservice.mail.sender.infraestructure.entry_point;


import com.proyecto.microservice.mail.sender.domain.common.ExceptionMailSender;
import com.proyecto.microservice.mail.sender.domain.common.ResponseMailSender;
import com.proyecto.microservice.mail.sender.domain.dto.MailDTO;
import com.proyecto.microservice.mail.sender.domain.dto.MailFileDTO;
import com.proyecto.microservice.mail.sender.domain.service.IMailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail-sender")
@RequiredArgsConstructor
public class MailSenderController {


    private final IMailSenderService iMailSenderService;

    @PostMapping(value = "/send-mail", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMailSender<MailDTO> sendMail(@RequestBody MailDTO mailDto){
        try{
            this.iMailSenderService.sendMail(mailDto);
            return ResponseMailSender.<MailDTO>builder().status(HttpStatus.OK.value()).code("OK-01").message("Mensaje enviado.").data(mailDto).build();
        }catch (ExceptionMailSender e) {
            throw new ExceptionMailSender(e.getStatus(), e.getCode(), e.getMessage());
        }catch (Exception e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-01", "Error Server internal");
        }
    }

    @PostMapping(value = "/send-mail-with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseMailSender<MailDTO> sendMailWithFile(@ModelAttribute MailFileDTO mailFileDto) {
        try {
            this.iMailSenderService.sendMailWithFile(mailFileDto);
            return ResponseMailSender.<MailDTO>builder().status(HttpStatus.OK.value()).code("OK-02").message("Send message with file.").data(mailFileDto).build();
        }catch (ExceptionMailSender e) {
            throw new ExceptionMailSender(e.getStatus(), e.getCode(), e.getMessage());
        }catch (Exception e) {
            throw new ExceptionMailSender(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IS-02", "Error Server internal");
        }
    }



}
