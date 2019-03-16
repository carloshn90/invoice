package com.carlos.invoice.server.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ValidIdExistInDtoValidator.class)
@Target({ METHOD, CONSTRUCTOR })
@Retention(RUNTIME)
public @interface ValidIdExistInDto {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
