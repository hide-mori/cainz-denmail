package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.RenrakuModel;
import com.tcs.denmail.online.domain.entity.RenrakuEntity;
import com.tcs.denmail.online.domain.service.renraku.RenrakuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 連絡文書別宛先Controller
 * 
 */
@RestController
public class RenrakuController extends TcsBaseController {


    @Autowired
    private RenrakuService renrakuService;

    @RequestMapping(path = "/t-renraku", method = RequestMethod.POST)
    public RenrakuEntity getRenrakuDetail(@RequestBody RenrakuModel renrakuModel) {

        RenrakuEntity renrakuEntity = renrakuService.syncData(renrakuModel);

        return renrakuEntity;
    }

}
