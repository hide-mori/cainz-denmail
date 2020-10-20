package com.tcs.denmail.online.app.controller;

import java.util.List;
import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.RenrakuFileModel;
import com.tcs.denmail.online.domain.service.renraku.file.RenrakuFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 連絡文書添付ファイル名Controller
 */
@RestController
public class RenrakuFileController extends TcsBaseController {

    @Autowired
    private RenrakuFileService renrakuFileService;

    @RequestMapping(path = "/t-renraku-file", method = RequestMethod.POST)
    public CooperationResponseModel dataCooperate(@RequestBody List<RenrakuFileModel> dataList) {

        return renrakuFileService.syncData(dataList);
    }

}
