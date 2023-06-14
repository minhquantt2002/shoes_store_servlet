package com.dev4fun.utils;

import com.dev4fun.dao.ProductDAO;
import com.dev4fun.model.Cart;
import com.dev4fun.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class CartUtil {
    public static ArrayList<Cart> getCart(HttpServletRequest request) {
        ArrayList<Cart> listCarts = (ArrayList<Cart>) SessionUtil.getInstance().getValue(request, "listCarts");
        if (listCarts == null) {
            listCarts = new ArrayList<>();
            SessionUtil.getInstance().putValue(request, "listCarts", listCarts);
        }
        return listCarts;
    }

    public static void addProductToCart(HttpServletRequest request, Product product, int size, int quantity) {
        ArrayList<Cart> listCarts = (ArrayList<Cart>) SessionUtil.getInstance().getValue(request, "listCarts");
        if (listCarts == null) {
            listCarts = new ArrayList<>();
        }
        int isExist = isExisting(listCarts, product.getId(), size);
        if (isExist != -1) {
            listCarts.get(isExist).setQuantity(Math.min(new ProductDAO().getQuantityBySize(listCarts.get(isExist).getProduct().getId(), size), listCarts.get(isExist).getQuantity() + quantity));
        } else {
            listCarts.add(new Cart(product, size, quantity));
        }
        SessionUtil.getInstance().putValue(request, "listCarts", listCarts);
    }

    public static void updateCart(HttpServletRequest request, String quantity, String size) {
        ArrayList<Cart> listCarts = (ArrayList<Cart>) SessionUtil.getInstance().getValue(request, "listCarts");
        for (Cart listCart : listCarts)
            if (listCart.getSize() == Integer.parseInt(size)) {
                listCart.setQuantity(Math.min(new ProductDAO().getQuantityBySize(listCart.getProduct().getId(), Integer.parseInt(size)), Integer.parseInt(quantity)));
            }
    }

    public static void removeProductInCart(HttpServletRequest request, int id, int size) {
        ArrayList<Cart> listCarts = (ArrayList<Cart>) SessionUtil.getInstance().getValue(request, "listCarts");
        listCarts.remove(isExisting(listCarts, id, size));
    }


    public static int isExisting(ArrayList<Cart> listCarts, int id, int size) {
        for (int i = 0; i < listCarts.size(); i++) {
            if (listCarts.get(i).getProduct().getId() == id && listCarts.get(i).getSize() == size) {
                return i;
            }
        }
        return -1;
    }
}
