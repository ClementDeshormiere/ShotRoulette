package com.example.shotroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BetResults extends AppCompatActivity {

    private List<WheelNumber> numberList = new ArrayList<>();
    private RecyclerView recyclerView ;
    private Result_Adapter myAdapter ;
    private PlayerDataBase playerDataBase;
    private Result_Adapter.RecyclerViewClickListener2 listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_results);

        playerDataBase = PlayerDataBase.getInstance(this);

        Toolbar toolbar = findViewById(R.id.my_toolbar_bet_results);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numberList.add(new WheelNumber(0,2));
        numberList.add(new WheelNumber(1,0));
        numberList.add(new WheelNumber(2,1));
        numberList.add(new WheelNumber(3,0));
        numberList.add(new WheelNumber(4,1));
        numberList.add(new WheelNumber(5,0));
        numberList.add(new WheelNumber(6,1));
        numberList.add(new WheelNumber(7,0));
        numberList.add(new WheelNumber(8,1));
        numberList.add(new WheelNumber(9,0));
        numberList.add(new WheelNumber(10,1));
        numberList.add(new WheelNumber(11,1));
        numberList.add(new WheelNumber(12,0));
        numberList.add(new WheelNumber(13,1));
        numberList.add(new WheelNumber(14,0));
        numberList.add(new WheelNumber(15,1));
        numberList.add(new WheelNumber(16,0));
        numberList.add(new WheelNumber(17,1));
        numberList.add(new WheelNumber(18,0));
        numberList.add(new WheelNumber(19,0));
        numberList.add(new WheelNumber(20,1));
        numberList.add(new WheelNumber(21,0));
        numberList.add(new WheelNumber(22,1));
        numberList.add(new WheelNumber(23,0));
        numberList.add(new WheelNumber(24,1));
        numberList.add(new WheelNumber(25,0));
        numberList.add(new WheelNumber(26,1));
        numberList.add(new WheelNumber(27,0));
        numberList.add(new WheelNumber(28,1));
        numberList.add(new WheelNumber(29,1));
        numberList.add(new WheelNumber(30,0));
        numberList.add(new WheelNumber(31,1));
        numberList.add(new WheelNumber(32,0));
        numberList.add(new WheelNumber(33,1));
        numberList.add(new WheelNumber(34,0));
        numberList.add(new WheelNumber(35,1));
        numberList.add(new WheelNumber(36,0));

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int winningNumber = bundle.getInt("winningNumber");

        for (int i = 0; i < playerDataBase.playerDao().getAll().size(); i++) {
            Log.d("boucle for = ", "ok");
            if (playerDataBase.playerDao().getAll().get(i).getBet_type() == 0 && (numberList.get(winningNumber).getColor() == 1)){
                Player player = playerDataBase.playerDao().getAll().get(i);
                Log.d("1st boucle if = ", "ok");
                if(numberList.get(winningNumber).getColor() == 1) {
                    player.setBet_result(player.getBet_number() * 2);
                    playerDataBase.playerDao().insert(player);
                    Log.d("test result = ", Integer.toString(player.getBet_number()));
                }
            }
            if (playerDataBase.playerDao().getAll().get(i).getBet_type() == 1 && (numberList.get(winningNumber).getColor() == 0)) {
                Player player = playerDataBase.playerDao().getAll().get(i);
                player.setBet_result(player.getBet_number() * 2);
                playerDataBase.playerDao().insert(player);
            }
            if (playerDataBase.playerDao().getAll().get(i).getBet_type() == 2 && (winningNumber % 2 == 0)) {
                Player player = playerDataBase.playerDao().getAll().get(i);
                player.setBet_result(player.getBet_number() * 2);
                playerDataBase.playerDao().insert(player);
            }
            if (playerDataBase.playerDao().getAll().get(i).getBet_type() == 3 && (winningNumber % 2 == 1)) {
                Player player = playerDataBase.playerDao().getAll().get(i);
                player.setBet_result(player.getBet_number() * 2);
                playerDataBase.playerDao().insert(player);
            }
            if (playerDataBase.playerDao().getAll().get(i).getBet_type() == 4 && (0 < winningNumber) && (winningNumber <= 12)) {
                Player player = playerDataBase.playerDao().getAll().get(i);
                player.setBet_result(player.getBet_number() * 3);
                playerDataBase.playerDao().insert(player);
            }
            if (playerDataBase.playerDao().getAll().get(i).getBet_type() == 5 && (winningNumber <= 24)) {
                Player player = playerDataBase.playerDao().getAll().get(i);
                player.setBet_result(player.getBet_number() * 3);
                playerDataBase.playerDao().insert(player);
            }
            if (playerDataBase.playerDao().getAll().get(i).getBet_type() == 6 && (24 < winningNumber) && (winningNumber <= 36)) {
                Player player = playerDataBase.playerDao().getAll().get(i);
                player.setBet_result(player.getBet_number() * 3);
                playerDataBase.playerDao().insert(player);
            }

            if (playerDataBase.playerDao().getAll().get(i).getBet_type() == 7 &&
                    (playerDataBase.playerDao().getAll().get(i).getWheel_number() == winningNumber)) {
                Player player = playerDataBase.playerDao().getAll().get(i);
                player.setBet_result(player.getBet_number() * 5);
                playerDataBase.playerDao().insert(player);
            }


        }

        setOnClickListener2();

        recyclerView = findViewById(R.id.myRecyclerView2);
        myAdapter = new Result_Adapter(playerDataBase.playerDao().getAll(),listener);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myAdapter);
    }
    private void setOnClickListener2() {
        listener = new Result_Adapter.RecyclerViewClickListener2() {
            @Override
            public void onClick(View v, int position) {
                Player player = playerDataBase.playerDao().getAll().get(position);
                player.setHave_drunk(true);
                playerDataBase.playerDao().insert(player);
                BetResults.this.recreate();
            }
        };
    }

    public void goHomePage(View view) {
        Intent intent = new Intent(BetResults.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}