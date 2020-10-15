package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.common.exception.TcsApplicationException;
import com.tcs.denmail.online.app.model.ShainDptModel;
import com.tcs.denmail.online.domain.service.ShainDptService;
import com.tcs.denmail.online.domain.entity.ShainDptEntity;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * WorksController
 */
@RestController
public class ShainDptController extends TcsBaseController {

    @Autowired
    private ShainDptService shainDptService;

    @RequestMapping(value = "/shaindpt", method = RequestMethod.POST)
    public List<ShainDptEntity> getShainDpt(@RequestBody List<ShainDptModel> shainDptList)
            throws TcsApplicationException {

        // TEST用（結果確認用）
        // System.out.println("***** Controller(Start) *****");
        // System.out.println("----- [InputData]=> " + shainDptList.toString());

        List<ShainDptEntity> shainDptEntities = shainDptService.getShainDpt(shainDptList);

        // TEST用（結果確認用 ※実際のOutputは確定後に実装予定）
        // System.out.println("***** Controller(End) *****");
        return shainDptEntities;
    }

}
