package com.proyecto.microservice.mail.sender.domain.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMailSender<T> {
    private int status;
    private String code;
    private String message;
    private T data;
}