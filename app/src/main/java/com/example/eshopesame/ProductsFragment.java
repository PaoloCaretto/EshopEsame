package com.example.eshopesame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eshopesame.adapter.OnItemClickListener;
import com.example.eshopesame.adapter.ProductsAdapter;
import com.example.eshopesame.data.Eshop;
import com.example.eshopesame.data.ProductsAsyncResponse;
import com.example.eshopesame.model.ProductModel;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {
    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Eshop().getProducts(new ProductsAsyncResponse() {
            @Override
            public void onSuccess(ArrayList<ProductModel> products) {
                ProgressBar progressBar = view.findViewById(R.id.loading);
                TextView loadingText = view.findViewById(R.id.loadingText);
                RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                ProductsAdapter productsAdapter = new ProductsAdapter(products, new OnItemClickListener() {
                    @Override
                    public void onItemClicked(ProductModel product) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.productsFragment, new ProductFragment(product), null)
                                .addToBackStack(null)
                                .setReorderingAllowed(true)
                                .commit();
                    }
                });
                recyclerView.setAdapter(productsAdapter);
                progressBar.setVisibility(View.GONE);
                loadingText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("Errore", e.getMessage());
                e.printStackTrace();
            }
        });

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }
}