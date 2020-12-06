package com.example.shotroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Wheel extends AppCompatActivity {

    EditText editText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);

        Toolbar toolbar = findViewById(R.id.my_toolbar_wheel);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Wheel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.editTextNumberWheel);

    }

    public void seeResults(View view) {
        Context context = getApplicationContext();
        Class destination = BetResults.class;
        Intent intent = new Intent(Wheel.this, destination);
        intent.putExtra("winningNumber", Integer.parseInt(editText.getText().toString()));
        startActivity(intent);
        finish();
    }


}