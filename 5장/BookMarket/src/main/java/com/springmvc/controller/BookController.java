package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;  

    @GetMapping
    public String requestBookList(Model model) { 
        List<Book> list = bookService.getAllBookList();
        model.addAttribute("bookList", list);  
        return "books"; 
    } 
    
 /*   @GetMapping("/all")  
    public String requestAllBooks(Model model) {  
        List<Book> list = bookService.getAllBookList(); 
        model.addAttribute("bookList", list); 
        return "books";
    } 
   */ 
    @GetMapping("/all")  
    public ModelAndView requestAllBooks() {
        ModelAndView modelAndView = new ModelAndView();  
        List<Book> list = bookService.getAllBookList();
        modelAndView.addObject("bookList", list);  
        modelAndView.setViewName("books");  
        return modelAndView; 
    }
	
	
}
