package com.springmvc.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;
import com.springmvc.exception.BookIdException;
import com.springmvc.exception.CategoryException;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import com.springmvc.validator.UnitsInStockValidator;
import com.springmvc.validator.BookValidator;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;  

    
   // @Autowired
   // private UnitsInStockValidator unitsInStockValidator;  // UnitsInStockValidator의 인스턴스 선언
    
    
    @Autowired
    private BookValidator bookValidator;  // BookValidator 인스턴스 선언
    
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
    
    @GetMapping("/{category}") 
    public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {  
        List<Book> booksByCategory =bookService.getBookListByCategory(bookCategory);  
        
        if (booksByCategory == null || booksByCategory.isEmpty()) {
            throw new CategoryException();
        }
        
        model.addAttribute("bookList", booksByCategory);  
        return "books";   
    }
    
    @GetMapping("/filter/{bookFilter}")
    public String requestBooksByFilter(
    @MatrixVariable(pathVar="bookFilter") Map<String,List<String>> bookFilter, 
    Model model) {
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);
        return "books";
    }
    
    @GetMapping("/book") 
    public String requestBookById(@RequestParam("id") String bookId, Model model) {  
        Book bookById = bookService.getBookById(bookId);
        model.addAttribute("book", bookById );
        return "book";
    }
    
    /*@GetMapping("/add")  
    public String requestAddBookForm(Book book) {  
        return "addBook";  
    }  
    */
    
    @GetMapping("/add")  
    public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {  
        return "addBook";
    }  
	
    @PostMapping("/add") 
    public String submitAddNewBook(@Valid @ModelAttribute("NewBook")  Book book, BindingResult result) {
    	 
    	if(result.hasErrors()) { 
             return "addBook";
         } 
        
    	 
    	MultipartFile bookImage = book.getBookImage();  

        String saveName = bookImage.getOriginalFilename();  
        File saveFile = new File("C:\\upload", saveName); 
        
        if (bookImage != null && !bookImage.isEmpty()) {
            try {
                bookImage.transferTo(saveFile);  
            } catch (Exception e) {
                throw new RuntimeException("도서 이미지 업로드가 실패하였습니다", e);
            }
        }
    	
        bookService.setNewBook(book); 
        return "redirect:/books"; 
    } 
    
    @ModelAttribute  
    public void addAttributes(Model model) { 
        model.addAttribute("addTitle", "신규 도서 등록");  
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	
    	//binder.setValidator(unitsInStockValidator);  // 생성한 unitsInStockValidator 설정 
    	 binder.setValidator(bookValidator);  
        binder.setAllowedFields("bookId","name","unitPrice","author", "description", 
        "publisher","category","unitsInStock","totalPages", "releaseDate", "condition", "bookImage"); 
    }
    
    @ExceptionHandler(value={BookIdException.class}) 
    public ModelAndView handleError(HttpServletRequest req, BookIdException exception) {
         ModelAndView mav = new ModelAndView();  
         mav.addObject("invalidBookId", exception.getBookId());  
         mav.addObject("exception", exception);  
         mav.addObject("url", req.getRequestURL()+"?"+req.getQueryString());  
         mav.setViewName("errorBook");  
         return mav;  
    }
    
    
}
