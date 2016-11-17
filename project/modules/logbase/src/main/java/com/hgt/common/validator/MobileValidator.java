package com.hgt.common.validator;

import com.hgt.common.utils.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public void initialize(Mobile arg0) {

    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext arg1) {
        boolean ret = true;
        if (mobile != null) {
            ret = StringUtil.isMobile(mobile);
        }
        return ret;
    }
}
