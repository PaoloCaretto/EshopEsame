package com.example.eshopesame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eshopesame.model.ProductModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;


public class ProductFragment extends Fragment {
    private ProductModel product;
    public ProductFragment(ProductModel product) {
        this.product = product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // carosello
        ImageCarousel imageCarousel = view.findViewById(R.id.carousel);
        ArrayList<CarouselItem> images = new ArrayList<>();
        for (String url : product.images) {images.add(new CarouselItem(url));}
        imageCarousel.addData(images);
        // testi
        TextView title = view.findViewById(R.id.title);
        TextView brand = view.findViewById(R.id.brand);
        TextView ogPrice = view.findViewById(R.id.ogPrice);
        TextView discount = view.findViewById(R.id.discount);
        TextView rating = view.findViewById(R.id.rating);
        TextView stock = view.findViewById(R.id.stock) ;
        TextView description = view.findViewById(R.id.description);
        title.setText(product.title);
        brand.setText(product.brand);
        ogPrice.setText("Original price: " + product.price + "$");
        discount.setText("Discount: " + product.discountPercentage + "%");
        stock.setText("Quantity available: " + product.stock);
        rating.setText("Rating: "+ product.rating);
        description.setText(product.description);

        if(product.discountPercentage == 0.0) {
            discount.setVisibility(View.GONE);
            ogPrice.setVisibility(View.GONE);
        }
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

}