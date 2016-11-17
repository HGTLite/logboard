package com.hgt.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MobileValidator.class)
public @interface Mobile {
    String message() default "mobile不是有效的手机号码格式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
