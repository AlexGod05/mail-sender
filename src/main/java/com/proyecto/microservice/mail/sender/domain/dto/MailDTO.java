package com.proyecto.microservice.mail.sender.domain.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDTO {
    private String toUser;
    private String subject;
    private String message;
}
