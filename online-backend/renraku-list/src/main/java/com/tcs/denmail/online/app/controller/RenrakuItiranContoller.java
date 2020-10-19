package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.online.domain.service.list.RenrakuListService;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.RenrakuItiranModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RenrakuItiranContoller  extends TcsBaseController {

    @Autowired
    private RenrakuListService renrakuListService;

    @GetMapping("/renrakulist/{shainCd}")
    public RenrakuItiranModel getRenrakuDetail(@PathVariable String shainCd) {
        // http://localhost:8080/renrakulist/01037233
        RenrakuItiranModel renrakuItiranModel = renrakuListService.getRenrakuItiranModel(shainCd);

        return renrakuItiranModel;
    }
    @GetMapping("/renrakulist")
    @ResponseBody
    public RenrakuItiranModel getRenrakuDetail2(@RequestParam("shainCd") String  shainCd) {
        // http://localhost:8080/renrakulist?shainCd=01037233
        RenrakuItiranModel renrakuItiranModel = renrakuListService.getRenrakuItiranModel(shainCd);

        return renrakuItiranModel;
    }

}