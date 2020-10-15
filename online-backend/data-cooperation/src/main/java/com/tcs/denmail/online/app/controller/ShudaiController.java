package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.ShudaiModel;
import com.tcs.denmail.online.domain.entity.ShudaiEntity;
import com.tcs.denmail.online.domain.service.shudai.ShudaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主題マスタController
 * 
 */
@RestController
public class ShudaiController extends TcsBaseController {

    @Autowired
    private ShudaiService shudaiService;

    @RequestMapping(path = "/m-shudai", method = RequestMethod.POST)
    public ShudaiEntity getRenrakuDetail(@RequestBody ShudaiModel shudaiModel) {

        ShudaiEntity shudaiEntity = shudaiService.syncData(shudaiModel);

        return shudaiEntity;
    }

}
