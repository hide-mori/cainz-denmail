package com.tcs.denmail.online.app.controller;

import java.util.List;
import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.VAtesakiModel;
import com.tcs.denmail.online.domain.service.vatesaki.VAtesakiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VAtesakiController extends TcsBaseController {

    @Autowired
    private VAtesakiService vAtesakiService;

    @RequestMapping(path = "/t-v-atesaki", method = RequestMethod.POST)
    public CooperationResponseModel getRenrakuDetail(@RequestBody List<VAtesakiModel> dataList) {

        return vAtesakiService.syncData(dataList);
    }

}
