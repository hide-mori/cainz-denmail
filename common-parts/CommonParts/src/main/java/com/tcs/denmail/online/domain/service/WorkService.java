package com.tcs.denmail.online.domain.service;

/**
 * WorkService
 */
public interface WorkService {

    WorkServiceOutDto getWorkDetail(WorkServiceInDto inDto);

    WorkServiceOutDto registWorkDetail(WorkServiceInDto inDto);

}