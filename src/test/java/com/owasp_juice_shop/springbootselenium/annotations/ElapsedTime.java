package com.owasp_juice_shop.springbootselenium.annotations;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ElapsedTime {
}
