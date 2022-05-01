package com.deo.microservices.smsservice.service;

import com.deo.microservices.smsservice.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.usage.Trigger;
import com.twilio.rest.studio.v1.flow.Execution;
import com.twilio.rest.studio.v2.Flow;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Log4j2
public class TwilioService {

    private final String fileUri = "https://microservices-sandbox-2316.twil.io/welcome";
    private final TwilioConfig config;

    public void sendSms(String messageText, String messageNumber) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());
        Message message = Message.creator(
                        new PhoneNumber(messageNumber),
                        "MG3e8b5225af02740148f4504591ee1a58",
                        messageText
                )
                .create();
        log.info(message.getStatus());
    }

    public void prerecordedCall(String callNumber) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());
        Call call = Call.creator(
                        new PhoneNumber(callNumber),
                        new PhoneNumber(config.getPhoneNumber()),
                        URI.create(fileUri))
                .create();
        log.info(call.getStatus());
    }

    public void twimlCall(String callNumber) {
        Twilio.init(config.getAccountSid(), config.getAuthToken());
        Call call = Call.creator(
                        new PhoneNumber(callNumber),
                        new PhoneNumber(config.getPhoneNumber()),
                        new Twiml("""
                                <Response>
                                    <Say>You have a new job request!</Say>
                                    <Pause length="1"/>
                                    <Say> Accept it</Say>
                                    <Pause length="1"/>
                                    <Say>Or decline</Say>
                                </Response>
                                """))
                .create();
        log.info(call.getStatus());

    }

    public void flowInit(String callNumber) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("data", "testdata");

        Twilio.init(config.getAccountSid(), config.getAuthToken());
        Execution execution = Execution.creator("FWd984cd6ee6b88103560ca475a2bf9b0a", new PhoneNumber(callNumber),
                new PhoneNumber(config.getPhoneNumber())).setParameters(params).create();

        log.info(execution.getStatus());
    }

}
