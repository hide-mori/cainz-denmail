package com.tcs.denmail.online.app.controller;

import java.util.List;
import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.RenrakuJokyoModel;
import com.tcs.denmail.online.domain.service.renraku.jokyo.RenrakuJokyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RenrakuJokyoController extends TcsBaseController {

    @Autowired
    private RenrakuJokyoService renrakuJokyoService;

    @RequestMapping(path = "/t-renraku-jokyo", method = RequestMethod.POST)
    public CooperationResponseModel getRenrakuDetail(
            @RequestBody List<RenrakuJokyoModel> dataList) {

        return renrakuJokyoService.syncData(dataList);
    }

}
