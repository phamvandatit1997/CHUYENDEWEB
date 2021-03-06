package com.subject.sell_cake.api.admin.response;

import com.subject.sell_cake.model.entity.Cart;

import java.util.List;

public class CartResponse {
    private List<Cart> listCart;
    private int numberProduct;
    private int sumPrice;

    public List<Cart> getListCart() {
        return listCart;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setListCart(List<Cart> listCart) {
        this.listCart = listCart;
    }

    public void setNumberProduct(int numberProduct) {
        this.numberProduct = numberProduct;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }
}
