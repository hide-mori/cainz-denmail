package com.tcs.denmail.online.app.controller;

import java.util.List;
import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.ShainDptModel;
import com.tcs.denmail.online.domain.service.shaindpt.ShainDptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ShainDptController
 */
@RestController
public class ShainDptController extends TcsBaseController {

    @Autowired
    private ShainDptService shainDptService;

    @RequestMapping(value = "/shaindpt", method = RequestMethod.POST)
    public CooperationResponseModel dataCooperate(@RequestBody List<ShainDptModel> dataList) {

        return shainDptService.syncData(dataList);
    }

}
