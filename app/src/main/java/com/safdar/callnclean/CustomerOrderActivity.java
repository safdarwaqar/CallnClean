package com.safdar.callnclean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
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
    private TextView order;
    private ArrayList<PackageData> packs;
    private int quantity = 1;
    private String totalPrice = "";
    private AlertDialog dialog;
    private RadioGroup radioGroup;


    private final String bikeWash = "Bike Foam-Wash, Polish @₹";
    private final String sedanWash = "Only Foam-Wash For Sedan/HatchBack Cars @₹";
    private final String suvWash = "Only Foam-Wash For SUV Cars @₹";
    private final String sedPremium = "Foam-Wash, Interior_Vacuum Cleaning + Polish, Sanitization For Sedan/HatchBack @₹";
    private final String suPremium = "Foam-Wash, Interior_Vacuum Cleaning, Polish, Sanitization For SUV @₹";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);

        //initialization
        db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name


        packs = new ArrayList<>();
        radioGroup = findViewById(R.id.radioGroup);
        final PriceData priceData = new PriceData("150", "250", "300", "350", "400");
        String[] fl = {"kane", "undertaker", "john", "dsfasdfadf", "asdfasdf", "fadfasdf"};
        PackageData packageData = new PackageData("Bike Wash", "this is a bike wash man lkhsldkfhl f laskdfhlksdhflkhfl    lkhasfl hlaskdfh ", null, "2", "150");

        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        packs.add(packageData);
        RecyclerView recyclerView = findViewById(R.id.rv_orderItems);
        recyclerView.setAdapter(new RecyclerViewAdapter(packs, getApplicationContext()));
        //updating order prices here...


        DocumentReference docRef = db.collection("dbOrders").document("orderPrice");

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w(TAG, "Listen failed.", error);
                    return;
                }

                if (value != null && value.exists()) {
                    Log.d(TAG, "Current data: " + value.getData());
                    PriceData priceData1 = value.toObject(PriceData.class);
                    assert priceData1 != null;
                    bikePrice = priceData1.getBikePrice();
                    sedHatchPremium = priceData1.getSedanHatchPremium();
                    suvPremium = priceData1.getSuvPremium();
                    sedHatchPrice = priceData1.getSedanHatchPrice();
                    suvPrice = priceData1.getSuvPrice();
                    Toast.makeText(getApplicationContext(), bikePrice, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "price:" + bikePrice);
                    orderPrices();
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void orderPrices() {

        CheckBox checkBoxBike = findViewById(R.id.cb_bikeWash);
        CheckBox checkBoxsedan = findViewById(R.id.cb_sedan_wash);
        CheckBox checkBoxSedanPrem = findViewById(R.id.sedan_premium);
        CheckBox checkBoxSuv = findViewById(R.id.cb_suvWash);
        CheckBox checkBoxSuvPrem = findViewById(R.id.cb_suvPremium);

        checkBoxBike.setText(bikeWash + bikePrice);
        checkBoxsedan.setText(sedanWash + sedHatchPrice);
        checkBoxSedanPrem.setText(sedPremium + sedHatchPremium);
        checkBoxSuv.setText(suvWash + suvPrice);
        checkBoxSuvPrem.setText(suPremium + suvPremium);
        onCheckListener(checkBoxBike, bikePrice);
        onCheckListener(checkBoxsedan, sedHatchPrice);
        onCheckListener(checkBoxSedanPrem, sedHatchPremium);
        onCheckListener(checkBoxSuv, suvPrice);
        onCheckListener(checkBoxSuvPrem, suvPremium);
    }

    public void onCheckListener(CheckBox checkBox, String getVehiclePrice) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout layout = findViewById(R.id.layout_qty);

                Boolean test = checkBox.isChecked();
                Log.i("bool", test.toString());
                if (checkBox.isChecked()) {
                    quantityLayout(getVehiclePrice);
                    Toast.makeText(CustomerOrderActivity.this, "" + checkBox.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CustomerOrderActivity.this, getVehiclePrice + "Checkbox unselected", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public String calculateOrderPrice(int quantityy, String price) {

        int toprice = quantityy * Integer.parseInt(price);
        Toast.makeText(this, String.valueOf(toprice), Toast.LENGTH_SHORT).show();
        return String.valueOf(toprice);


    }


    public void btnPlaceOrder(View view) {
        PackageData packageData = new PackageData("Bike Wash", "We are here to Wash and Polish Your Bike", "null", null, "150");
        CollectionReference ref = FirebaseFirestore.getInstance().collection("packages");
        ref.document("sedanPremiumDetails").set(packageData).addOnSuccessListener(aVoid -> Toast.makeText(CustomerOrderActivity.this, "saved the doc!", Toast.LENGTH_SHORT).show());
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();


    }

   /* public void save_onButtonClick(View view) {
        TextView data = findViewById(R.id.tv_chkbtn_summary);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        String radioQuantity =
                ((RadioButton) findViewById(rg.getCheckedRadioButtonId()))
                        .getText().toString();

//        data.setText(calculateOrderPrice(Integer.parseInt(radQ), "200"));

        Toast.makeText(this, "" + data, Toast.LENGTH_SHORT).show();

    }
*/


    public void quantityLayout(String selectedCheckboxPrice) {

        Dialog dialog = new Dialog(CustomerOrderActivity.this);
        dialog.setContentView(R.layout.quantity_layout);
        TextView data = findViewById(R.id.tv_chkbtn_summary);
        Button saveData = dialog.findViewById(R.id.save_button_qty_btn);
        RadioGroup rg = dialog.findViewById(R.id.radioGroup);
        dialog.show();

        saveData.setOnClickListener(v -> {
            RadioButton radioButton = dialog.findViewById(rg.getCheckedRadioButtonId());
            if (radioButton != null) {
                String radioQuantity = radioButton.getText().toString();

                String calcPrice = "";
                calcPrice = calculateOrderPrice(Integer.parseInt(radioQuantity), selectedCheckboxPrice);
                concat += calcPrice;
                data.setText(concat);
                dialog.dismiss();
            } else {

                Toast.makeText(this, "select any Quantity!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}

