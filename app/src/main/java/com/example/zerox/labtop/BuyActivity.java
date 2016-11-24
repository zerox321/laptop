package com.example.zerox.labtop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zerox.labtop.Model.Laptop;
import com.example.zerox.labtop.Model.order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuyActivity extends AppCompatActivity {

    EditText edName;
    EditText edPhone;
    Laptop laptop;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        edName = (EditText) findViewById(R.id.ETName);
        edPhone = (EditText) findViewById(R.id.ETPhone);


        laptop = getIntent().getParcelableExtra(Detail_Activity.EXTRA_OBJECT);
        Button orderBtn = (Button) findViewById(R.id.btnOrder);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(edName.getText().toString(), edPhone.getText().toString(), laptop);
                edName.setText("");
                edPhone.setText("");
                Toast.makeText(BuyActivity.this, "Order is done !", Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setValue(String name, String phone, Laptop laptop) {
        order order = new order(name, phone, laptop.getTitle(), laptop.getPrice());

        mDatabase.child("orders").child(String.valueOf(System.currentTimeMillis())).setValue(order);


    }

}
