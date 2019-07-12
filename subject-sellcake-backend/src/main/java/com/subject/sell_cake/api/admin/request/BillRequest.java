package com.subject.sell_cake.api.admin.request;

import com.subject.sell_cake.model.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillRequest implements Serializable {
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
