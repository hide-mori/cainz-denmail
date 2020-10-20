package com.tcs.denmail.online.app.controller;

import java.util.List;
import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.VShainMstModel;
import com.tcs.denmail.online.domain.service.shainmst.VShainMstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社員マスタビューController
 */
@RestController
public class VShainMstController extends TcsBaseController {

    @Autowired
    private VShainMstService vShainMstService;

    @RequestMapping(path = "/t-v-shain-mst", method = RequestMethod.POST)
    public CooperationResponseModel dataCooperate(@RequestBody List<VShainMstModel> dataList) {

        return vShainMstService.syncData(dataList);
    }

}
