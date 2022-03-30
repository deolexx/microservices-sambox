package com.deo.microservices.smsservice.service;

import com.deo.microservices.smsservice.config.TwilioConfig;
import com.deo.microservices.smsservice.model.MessageInput;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class TwilioSimpleSmsService {

    private final TwilioConfig config;

    public String send(MessageInput input) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());

        Message message = Message.creator(
                        new PhoneNumber(input.getNumber().isEmpty() ? "+380967986875" : input.getNumber()),
                        new PhoneNumber(config.getPhoneNumber()),
                        input.getMessage()

                )
                .create();

        return message.getStatus().toString();
    }

    public String call(String name) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());
        Call call = Call.creator(
                        new PhoneNumber("+380967986875"),
                        new PhoneNumber(config.getPhoneNumber()),
                        URI.create(name.equals("two")?"https://microservices-sandbox-2316.twil.io/testtwo.mp3":"https://microservices-sandbox-2316.twil.io/testone.mp3"))
                .create();

        return call.getSid();
    }
}
