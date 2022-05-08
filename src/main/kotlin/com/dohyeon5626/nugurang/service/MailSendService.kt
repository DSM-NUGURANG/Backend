package com.dohyeon5626.nugurang.service

import org.springframework.boot.autoconfigure.mail.MailProperties
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Component
class MailSendService (
    private val mailSender: JavaMailSender,
    private val mailProperties: MailProperties
) {
    private val fromAddress: InternetAddress = InternetAddress(mailProperties.username, "누구랑")

    fun sendMail(toAddress: String, title: String, content: String) {
        val message: MimeMessage = mailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(message, false, "UTF-8")
        messageHelper.setTo(toAddress)
        messageHelper.setFrom(fromAddress)
        messageHelper.setSubject(title)
        messageHelper.setText(content, true)
        mailSender.send(message)
    }
}