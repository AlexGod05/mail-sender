package com.proyecto.microservice.mail.sender.util;

public final class Constantes {

    private Constantes() {
    }

    // GENERAL
    public static final String PREFIX_MAIL_ATTACHMENT = "mail_attachment_";

    // OK
    public static final String CODE_OK_01 = "OK-01";
    public static final String MESSAGE_OK_01 = "Mensaje enviado.";
    public static final String CODE_OK_02 = "OK-02";
    public static final String MESSAGE_OK_02 = "Send message with file.";

    // INTERNAL_SERVER_ERROR
    public static final String CODE_IS_01 = "IS-01";
    public static final String MESSAGE_IS_01 = "Error Server internal";
    public static final String CODE_IS_02 = "IS-02";
    public static final String MESSAGE_IS_02 = "Error Created File Temp";
    public static final String CODE_IS_03 = "IS-03";
    public static final String MESSAGE_IS_03 = "Error created MimeMessage";
    public static final String CODE_IS_04 = "IS-04";
    public static final String MESSAGE_IS_04 = "Error Delete File Temp";
}
