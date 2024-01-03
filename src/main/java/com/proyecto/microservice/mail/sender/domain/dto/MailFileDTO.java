package com.proyecto.microservice.mail.sender.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailFileDTO extends MailDTO {

    @JsonIgnore
    private MultipartFile multipartFile;
}
