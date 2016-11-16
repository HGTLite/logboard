package com.hgt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.yaoooo.common.constants.RestConst;
import io.yaoooo.common.utils.StringUtil;
import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.LoginBundleBean;
import io.yaoooo.domain.SimpleStringBean;
import io.yaoooo.entity.WpUserInfo;
import io.yaoooo.service.WpUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/******************************************************************************
 * Created by  Yao  on  2016/7/31
 * README:用户账户相关处理类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@Api(value = "已完成登陆，未完成注册……", description = "关于用户信息操作的接口")
@RestController
public class UserAccountsController {

    private final String BASE_URL = "users";

    @Autowired
    WpUserInfoService wpUserInfoService;

    @ApiOperation(value="用户登录接口", notes="已完成用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginBundle", value = "用户名+混淆密码的混合信息，拼凑成json", required = true,
                    dataType = "string", paramType = "query")
    })
    @RequestMapping(value = BASE_URL + "/login", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> userPwdValidate(@RequestBody String loginBundle) {

        DataResult<SimpleStringBean> result = null;
        ObjectMapper mapper = new ObjectMapper();
        LoginBundleBean loginBundleBean = null;
        String username = "";
        String userpwd = "";

        try {
            loginBundleBean = mapper.readValue(loginBundle, LoginBundleBean.class);
            username = loginBundleBean.getUsername();
            userpwd = loginBundleBean.getUserpwd();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (loginBundle == null || username.trim() == "" || userpwd.trim() == "") {
            result = new DataResult<>(RestConst.ErrorCode.VALIDATE_FAIL, "登陆失败；用户名或密码为空！");
        } else {
            DataResult dr1 = wpUserInfoService.getUserInfo(username);
            if (dr1.getData() != null) {
                ///如果用户名存在
                WpUserInfo wpUserInfo = (WpUserInfo) dr1.getData();
                String dbPwd = wpUserInfo.getUserPwdHash();

                if (dbPwd.equals(userpwd)) {
                    ///如果密码正确
                    result = new DataResult<>(new SimpleStringBean(username + "登录成功；token是" + StringUtil.getRandomString(12)));
                } else {
                    ///如果密码错误
                    result = new DataResult<>(RestConst.ErrorCode.VALIDATE_FAIL, "验证失败；用户名或密码错误！");
                }
            } else {
                ///如果用户名不存在
                result = new DataResult<>(RestConst.ErrorCode.VALIDATE_FAIL, "验证失败；用户名或邮箱不存在！");
            }
        }
        return result;
    }


    @ApiOperation(value="获取与密码配对的随机字符串", notes="每次登陆都会先从服务器获取一个随机字符串")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true,
                    dataType = "string", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/login/hash/{userName:.+}", method = RequestMethod.GET)
    public DataResult<SimpleStringBean> getHash(@PathVariable String userName) {

        DataResult<WpUserInfo> userInfo = wpUserInfoService.getUserInfo(userName);

        WpUserInfo wpUserInfo = new WpUserInfo();

        wpUserInfo = userInfo.getData();


        DataResult<SimpleStringBean> result = null;

        if (wpUserInfo != null) {
            ///如果用户名存在
            result = new DataResult<>(new SimpleStringBean(wpUserInfo.getUserPwdSalt()));
        } else {
            ///如果用户名不存在
            result = new DataResult<>(RestConst.ErrorCode.VALIDATE_FAIL, "用户名或邮箱不存在");
        }

        return result;
    }


}
