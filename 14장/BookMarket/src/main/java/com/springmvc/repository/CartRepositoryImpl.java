package com.springmvc.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.springmvc.domain.Cart;

@Repository
public class CartRepositoryImpl implements CartRepository{

    private Map<String, Cart> listOfCarts;

    public CartRepositoryImpl() {
        listOfCarts = new HashMap<String,Cart>();
    }

    public Cart create(Cart cart) {  
        if(listOfCarts.keySet().contains(cart.getCartId())) {
            throw new IllegalArgumentException(String.format("��ٱ��ϸ� ������ �� �����ϴ�. ��ٱ��� id(%)�� �����մϴ�", cart.getCartId()));
        }

        listOfCarts.put(cart.getCartId(), cart);
        return cart;
    }  

    public Cart read(String cartId) { 
        return listOfCarts.get(cartId);
    } 
    
    public void update(String cartId, Cart cart) {
        if(!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException(String.format("��ٱ��� ����� ������ �� �����ϴ�. ��ٱ��� id(%)�� �������� �ʽ��ϴ�",cartId));  // ��ٱ��� ID�� �������� ���� ��� ���� ó��
        }
        listOfCarts.put(cartId, cart);
    }
    public void delete(String cartId) {
        if(!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException(String.format("��ٱ��� ����� ������ �� �����ϴ�. ��ٱ��� id(%)�� �������� �ʽ��ϴ�",cartId));  // ��ٱ��� ID�� �������� ������ ���� ó��
        }
        listOfCarts.remove(cartId);
    }
}