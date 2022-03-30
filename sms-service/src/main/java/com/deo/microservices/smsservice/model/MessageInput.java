package com.deo.microservices.smsservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@Setter
public class MessageInput {
@NotBlank
private String number;
@NotBlank
private String message;
}
