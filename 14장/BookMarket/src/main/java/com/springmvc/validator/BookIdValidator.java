package com.springmvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.springmvc.exception.BookIdException;
import com.springmvc.service.BookService;
import com.springmvc.domain.Book;

public class BookIdValidator implements ConstraintValidator<BookId, String>{

    @Autowired
    private BookService bookService;

    public void initialize(BookId constraintAnnotation) {  // @BookId ���� �ʱ�ȭ �޼���  
    }  
    public boolean isValid(String value, ConstraintValidatorContext context) {  // ��ȿ�� �˻� �޼���  
        Book book;
        try {
            book = bookService.getBookById(value);
        } catch (BookIdException e) {
            return true;
        }
        if(book!= null) {
            return false;
        }
        return true;
    } 
}