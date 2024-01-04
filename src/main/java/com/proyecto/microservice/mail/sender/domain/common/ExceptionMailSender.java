package com.proyecto.microservice.mail.sender.domain.common;

import lombok.Getter;

@Getter
public class ExceptionMailSender extends RuntimeException {
    private final int status;
    private final String code;
    private final String message;

    public ExceptionMailSender(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
