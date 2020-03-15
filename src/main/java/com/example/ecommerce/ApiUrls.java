package com.example.ecommerce;

public interface ApiUrls {

    String ROOT_URL_ORDERS = "api/orders";
    String URL_ORDERS_ORDER ="/{orderId}";
    String URL_ORDERS_ORDER_EMAIL ="/search/byEmail";

    String ROOT_URL_ITEMs = "api/items";
    String URL_ITEMS_ITEM ="/{itemId}";
}
