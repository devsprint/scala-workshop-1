package io.devsprint.java;

import java.util.ArrayList;
import java.util.List;

public class Demo {

   public static void main(String[] args) {

       List<String> list = new ArrayList<>();
       list.add("1");
       list.add("2");
       list.add("3");

       List<Integer> ints = new ArrayList<>();
       for (String s: list) {
           ints.add(Integer.parseInt(s));
       }




   }

   List<Order> orders = new ArrayList<>();

   private List<Product> getProducts() {
       List<Product> products = new ArrayList<>();
       for (Order order: orders) {
           products.addAll(order.getProducts());
       }

       return products;
   }

}

abstract class Product{};
abstract class Order{
    public List<Product> getProducts() {

        return null;
    }
};
