package com.springmvc.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import javax.validation.Constraint;

@Constraint(validatedBy = BookIdValidator.class) 
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented  
public @interface BookId {
	String message() default "{BookId.NewBook.bookId}";  
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};  
}