package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.WorkDetail;
import com.tcs.denmail.online.app.model.WorksResponseModel;
import com.tcs.denmail.online.domain.service.WorkService;
import com.tcs.denmail.online.domain.service.WorkServiceInDto;
import com.tcs.denmail.online.domain.service.WorkServiceOutDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WorksController
 */
@RestController
public class WorkController extends TcsBaseController {

    @Autowired
    private WorkService workService;

    @GetMapping(value = "/works")
    public WorksResponseModel getWorks() {

        WorkServiceOutDto outDto = workService.getWorkDetail(new WorkServiceInDto());

        WorksResponseModel worksResponseModel = new WorksResponseModel();
        worksResponseModel.setWorkList(outDto.getWorkList());

        return worksResponseModel;
    }

    @GetMapping("/works/{workId}/{workDetailId}")
    public WorkDetail getWorkdetail(@PathVariable String workId, @PathVariable String workDetailId) {

        WorkDetail workDetail = new WorkDetail();

        return workDetail;
    }

    @GetMapping(value = "/works/regist")
    public WorksResponseModel registWorks() {

        // WorkServiceOutDto outDto =
        workService.registWorkDetail(new WorkServiceInDto());

        WorksResponseModel worksResponseModel = new WorksResponseModel();
        // worksResponseModel.setWorkList(outDto.getWorkList());

        return worksResponseModel;
    }

}