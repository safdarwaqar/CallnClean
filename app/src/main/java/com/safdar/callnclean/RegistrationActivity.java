package com.safdar.callnclean;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.safdar.callnclean.Data.UserData;

public class RegistrationActivity extends AppCompatActivity {
    private Spinner spinner;
    private ProgressBar progressBar;
    private Button register;
    private EditText userName, userAddress;
    String loc,phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //initialization
        progressBar = findViewById(R.id.progressBar_reg);
        progressBar.setVisibility(View.GONE);
        spinner = findViewById(R.id.spinner_location);
        register = findViewById(R.id.btn_register);
        userName = findViewById(R.id.et_name_reg);
        userAddress = findViewById(R.id.et_address);
        loc="";
        phoneNumber=getIntent().getStringExtra("signInCredentials");

        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();





        String[] location = new String[]{"Asansol", "Kulti", "Neamatpur", "Barakar", "Dhanbad", "Maithon"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, location);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //getting selected item from spinner...
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //saving userData to FireStore
        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String name = userName.getText().toString();
                String address = userAddress.getText().toString();


                //creating userData object...
                UserData userData = new UserData(name, address, phoneNumber, loc);


                //storing data into database...
                db.collection("users").document(phoneNumber).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Data Saved!", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "failed to save data!!!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}