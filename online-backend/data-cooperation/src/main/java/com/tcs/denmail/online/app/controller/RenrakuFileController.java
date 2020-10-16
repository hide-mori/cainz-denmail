package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.RenrakuFileModel;
import com.tcs.denmail.online.domain.entity.RenrakuFileEntity;
import com.tcs.denmail.online.domain.service.renraku.file.RenrakuFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RenrakuFileController extends TcsBaseController {

    @Autowired
    private RenrakuFileService renrakuFileService;

    @RequestMapping(path = "/t-renraku-file", method = RequestMethod.POST)
    public RenrakuFileEntity getRenrakuDetail(@RequestBody RenrakuFileModel renrakuFileModel) {

        RenrakuFileEntity renrakuFileEntity = renrakuFileService.syncData(renrakuFileModel);

        return renrakuFileEntity;
    }

}
