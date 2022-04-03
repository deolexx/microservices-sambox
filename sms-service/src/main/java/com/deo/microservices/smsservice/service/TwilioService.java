package com.deo.microservices.smsservice.service;

import com.deo.microservices.smsservice.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Log4j2
public class TwilioService {

    private final TwilioConfig config;

    public void sendSms(String messageText, String messageNumber) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());
        Message message = Message.creator(
                        new PhoneNumber(messageNumber),
                        new PhoneNumber(config.getPhoneNumber()),
                        messageText
                )
                .create();
        log.info(message.getStatus());
    }

    public void prerecordedCall(String fileURI, String callNumber) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());
        Call call = Call.creator(
                        new PhoneNumber(callNumber),
                        new PhoneNumber(config.getPhoneNumber()),
                        URI.create(fileURI))
                .create();
        log.info(call.getStatus());
    }
}
