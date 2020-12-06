package com.example.shotroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    PlayerDataBase playerDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerDataBase = PlayerDataBase.getInstance(this);

    }

    public void onClick(View view) {
        Context context = getApplicationContext();
        Class destination = MainActivity2.class;
        Intent intent = new Intent(MainActivity.this, destination);

        for (int i=0;i<playerDataBase.playerDao().getAll().size();i++){
            Player player = playerDataBase.playerDao().getAll().get(i);
            player.setBet_result(0);
            player.setHave_drunk(false);

            playerDataBase.playerDao().insert(player);
        }
        startActivity(intent);
        finish();
    }
}