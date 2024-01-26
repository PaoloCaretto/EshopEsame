package com.example.eshopesame.data;

import com.example.eshopesame.model.ProductModel;

import java.util.ArrayList;

public interface ProductsAsyncResponse {
    void onSuccess(ArrayList<ProductModel> products);
    void onFailure(Exception e);
}
