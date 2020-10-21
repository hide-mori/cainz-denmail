package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.RenrakuDetailModel;
import com.tcs.denmail.online.domain.service.renraku.RenrakuDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 連絡文章詳細Controller
 * 
 */
@RestController
public class RenrakuDetailController extends TcsBaseController {

    @Autowired
    private RenrakuDetailService renrakuDetailService;

    @GetMapping("/renraku/{kanriNo}")
    public RenrakuDetailModel getRenrakuDetail(@PathVariable String kanriNo, @RequestParam("atesakiTenpoCd") String atesakiTenpoCd) {

        RenrakuDetailModel renrakuDetail = renrakuDetailService.getRenrakuDetail(kanriNo, atesakiTenpoCd);

        return renrakuDetail;
    }
    
    @GetMapping("/renraku")
    public RenrakuDetailModel getRenrakuDetail22(@RequestParam("kanriNo") String kanriNo, @RequestParam("atesakiTenpoCd") String atesakiTenpoCd) {

        RenrakuDetailModel renrakuDetail = renrakuDetailService.getRenrakuDetail(kanriNo, atesakiTenpoCd);

        return renrakuDetail;
    }

}