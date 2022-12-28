package com.prueba.AmigoInvisible.service;

public interface EmailSenderService {

    void sendEmail(String toEmail, String subject, String body);
}
