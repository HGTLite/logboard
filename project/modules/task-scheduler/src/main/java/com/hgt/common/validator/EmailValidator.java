package com.hgt.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************
 * Created by  Yao  on  2016/8/1
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class EmailValidator {

    /**
     * 验证邮箱格式
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {

        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
