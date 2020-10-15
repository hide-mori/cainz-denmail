package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.AtesakiCdModel;
import com.tcs.denmail.online.domain.entity.AtesakiCdEntity;
import com.tcs.denmail.online.domain.service.atesakicd.AtesakiCdService;
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
    private AtesakiCdService atesakiCdService;

    @RequestMapping(path = "/t-renraku", method = RequestMethod.POST)
    public AtesakiCdEntity getRenrakuDetail(@RequestBody AtesakiCdModel atesakiCdModel) {

        AtesakiCdEntity atesakiCdEntity = atesakiCdService.syncData(atesakiCdModel);

        return atesakiCdEntity;
    }

}
