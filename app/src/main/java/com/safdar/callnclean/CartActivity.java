package com.safdar.callnclean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.safdar.callnclean.Adapter.CartRecyclerViewAdapter;
import com.safdar.callnclean.Data.PackageData;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    CartRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        String itemName = getIntent().getStringExtra("gettingItemName");
        String itemPrice = getIntent().getStringExtra("gettingItemPrice");
        PackageData packageData = new PackageData(itemName, null, itemPrice, null, null);
        recyclerView = findViewById(R.id.rv_cart);
        FirebaseFirestore db= FirebaseFirestore.getInstance();


        Query query = db.collection("addToCart");
        FirestoreRecyclerOptions<PackageData> options= new FirestoreRecyclerOptions.Builder<PackageData>()
                .setQuery(query,PackageData.class).build();
       adapter= new CartRecyclerViewAdapter(options,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}