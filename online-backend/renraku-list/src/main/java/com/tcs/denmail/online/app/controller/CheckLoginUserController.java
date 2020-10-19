package com.tcs.denmail.online.app.controller;

import com.tcs.denmail.online.domain.service.check.CheckLoginUserService;

import com.tcs.denmail.common.controller.TcsBaseController;
import com.tcs.denmail.online.app.model.LoginUserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@RestController
public class CheckLoginUserController  extends TcsBaseController {

    @Autowired
    private CheckLoginUserService checkLoginUserService;

    @GetMapping("/checkloginuser/{shainCd}")
    public LoginUserModel getLoginUser(@PathVariable String shainCd) {
        // http://localhost:8080/checkloginuser/01037233
        return getLoginUserModel(shainCd);

    }
    @GetMapping("/checkloginuser")
    public LoginUserModel getLoginUser2(@RequestParam("shainCd") String  shainCd) {
        // http://localhost:8080/checkloginuser?shainCd=01037233
        return getLoginUserModel(shainCd);
    }

    @PostMapping("/checkloginuserpost")
    @ResponseStatus(HttpStatus.CREATED)
    private LoginUserModel getLoginUserModel3(@RequestBody LoginUserModel shainCd) {
        return getLoginUserModel(shainCd.getShainCd());
    }

    private LoginUserModel getLoginUserModel(String shainCd) {
        System.out.println("shainCd==========="+ shainCd);
        LoginUserModel model = checkLoginUserService.getLoginUserModel(shainCd);

        return model;
    }
}
