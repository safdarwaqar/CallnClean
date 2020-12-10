package com.safdar.callnclean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hbb20.CountryCodePicker;

public class PhoneRegistrationActivity extends AppCompatActivity {
    private EditText phoneNo, enterOtp;
    private Button sendOtp;
    private CountryCodePicker codePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_registration);
        phoneNo = findViewById(R.id.et_phone_no);
        sendOtp = findViewById(R.id.btn_send_otp);
        codePicker = findViewById(R.id.ccp);
        codePicker.registerCarrierNumberEditText(phoneNo);

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VerifyOtpActivity.class);
                intent.putExtra("phone", codePicker.getFullNumberWithPlus().trim());
                startActivity(intent);


            }
        });

    }
}