package com.example.shotroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.Objects;

public class NewPlayer extends AppCompatActivity {

    EditText editText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);

        Toolbar toolbar = findViewById(R.id.my_toolbar_new_player);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("New Player");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.editTextPersonName);
    }

    public void onClickAdd(View view) {
        Context context = getApplicationContext();
        Class destination = MainActivity2.class;
        Intent intent = new Intent(NewPlayer.this, destination);
        intent.putExtra("key", editText.getText().toString());
        startActivity(intent);
        finish();
    }

}