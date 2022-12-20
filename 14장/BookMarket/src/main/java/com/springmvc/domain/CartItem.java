package com.springmvc.domain;

public class CartItem {
    private Book book;  // 도서
    private int quantity;  // 도서 개수
    private int totalPrice;  // 도서 가격

    public CartItem() {  
        // TODO Auto-generated constructor stub
    }  

    public CartItem(Book book) {  
        super();
        this.book = book;
        this.quantity = 1;
        this.totalPrice = book.getUnitPrice();
    } 

    public Book getBook() {  
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        this.updateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    public int getTotalPrice() {
        return totalPrice;
    }  

    public void updateTotalPrice() {  
        totalPrice = this.book.getUnitPrice()*this.quantity;
    }  

    @Override
    public int hashCode() { 
        final int prime = 31;
        int result = 1;
        result = prime * result + ((book == null) ? 0 : book.hashCode());
        return result;
    }  

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartItem other = (CartItem) obj;
        if (book == null) {
            if (other.book != null)
                return false;
        } else if (!book.equals(other.book))
            return false;
        return true;
    }  

}