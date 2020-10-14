package com.tcs.denmail.online.domain.service;

public interface IfService<In, Out> {

    Out execute(In inDto);
}
