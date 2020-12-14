package com.safdar.callnclean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.safdar.callnclean.Adapter.RecyclerViewAdapter;
import com.safdar.callnclean.Data.PackageData;
import com.safdar.callnclean.Data.PriceData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerOrderActivity extends AppCompatActivity {

    private static final String TAG = "CustomerOrderActivity";
    private Spinner spinner;
    public String bikePrice = "null";
    public String sedHatchPrice = "";
    public String suvPrice = "", concat = "";
    public String suvPremium = "null";
    public String sedHatchPremium = "";
    private FirebaseFirestore db;
    private RecyclerViewAdapter adapter;
    private TextView order;
    private RecyclerView recyclerView;
    private int quantity = 1;
    private String totalPrice = "";


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);

        //initialization
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rv_orderItems);

        // Create a new user with a first and last name


        Query query = db.collection("packages");
        FirestoreRecyclerOptions<PackageData> options = new FirestoreRecyclerOptions.Builder<PackageData>()
                .setQuery(query, PackageData.class).build();
        adapter = new RecyclerViewAdapter(options, getApplicationContext());
        recyclerView.setAdapter(adapter);

    }


    public String calculateOrderPrice(int quantityy, String price) {

        int toprice = quantityy * Integer.parseInt(price);
        Toast.makeText(this, String.valueOf(toprice), Toast.LENGTH_SHORT).show();
        return String.valueOf(toprice);


    }


    public void btnPlaceOrder(View view) {
        startActivity(new Intent(getApplicationContext(), CartActivity.class));
    }
}

