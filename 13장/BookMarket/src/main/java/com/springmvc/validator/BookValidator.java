package com.springmvc.validator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.springmvc.domain.Book;

public class BookValidator implements Validator{

    @Autowired
    private javax.validation.Validator beanValidator;  // bean validation의 인스턴스 선언

    private Set<Validator> springValidators;  //spring validation의 인스턴스 선언
    public BookValidator() {   //생성자 
        springValidators = new HashSet<Validator>();
    }  

    public void setSpringValidators(Set<Validator> springValidators) {  // springValidators의 setter() 메서드 
        this.springValidators = springValidators;
    } 

    public boolean supports(Class<?> clazz) {  // Book 클래스의 유효성 검사를 위한 여부 메서드 
        return Book.class.isAssignableFrom(clazz);
    }  
    public void validate(Object target, Errors errors) {  // Book 클래스의 유효성 검사 메서드 
        Set<ConstraintViolation<Object>> violations = beanValidator.validate(target);  // Bean Validation 설정
        for (ConstraintViolation<Object> violation : violations) {  // bean validation 오류 저장
            String propertyPath = violation.getPropertyPath().toString();  // 오류 발생 필드 저장
            String message = violation.getMessage();  // 오류 발생 메시지 저장
            errors.rejectValue(propertyPath, "", message);  // 오류 발생된 필드와 메시지를 Errors 객체에 저장
        }  
        for(Validator validator: springValidators) {  // spring validation 오류 저장 
            validator.validate(target, errors);  // 발생된 오류 정보를 전달
        }  
    }  
}