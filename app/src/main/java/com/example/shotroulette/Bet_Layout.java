package com.example.shotroulette;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bet_Layout extends AppCompatActivity {

    PlayerDataBase playerDataBase;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    EditText editTextNumber;
    EditText editTextBet;
    Button applyButton;
    Boolean valuebtwn0and36 = true;
    Boolean bet_sip = false;

    List<Integer> listButtonID = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        Toolbar toolbar = findViewById(R.id.my_toolbar_bet);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Bet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        playerDataBase = PlayerDataBase.getInstance(this);

        listButtonID.add(findViewById(R.id.blackButton).getId());
        listButtonID.add(findViewById(R.id.redButton).getId());
        listButtonID.add(findViewById(R.id.pairButton).getId());
        listButtonID.add(findViewById(R.id.impairButton).getId());
        listButtonID.add(findViewById(R.id.sequenceButton1).getId());
        listButtonID.add(findViewById(R.id.sequenceButton2).getId());
        listButtonID.add(findViewById(R.id.sequenceButton3).getId());
        listButtonID.add(findViewById(R.id.numberChoice).getId());

        String playerName = "Player";
        int playerID = 0 ;
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            //playerName=extras.getString("playerName");
            playerID=extras.getInt("playerID");
            playerName=playerDataBase.playerDao().getAll().get(playerID).getName();
        }

        final List<Player> playerList = playerDataBase.playerDao().getAll();
        radioGroup = findViewById(R.id.wideRadioGroup);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextBet = findViewById(R.id.editTextBet);
        textView = findViewById(R.id.titleText);
        textView.setText(playerName + " make your bet !");
        applyButton = findViewById(R.id.applyButton);
        final int finalPlayerID = playerID;
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                Player player_changed = playerDataBase.playerDao().getAll().get(finalPlayerID);

                player_changed.setBet_number(Integer.parseInt(editTextBet.getText().toString()));

                if (radioId==listButtonID.get(0)){
                    player_changed.setBet_type(0);
                }
                if (radioId==listButtonID.get(1)){
                    player_changed.setBet_type(1);
                }
                if (radioId==listButtonID.get(2)){
                    player_changed.setBet_type(2);
                }
                if (radioId==listButtonID.get(3)){
                    player_changed.setBet_type(3);
                }
                if (radioId==listButtonID.get(4)){
                    player_changed.setBet_type(4);
                }
                if (radioId==listButtonID.get(5)){
                    player_changed.setBet_type(5);
                }
                if (radioId==listButtonID.get(6)){
                    player_changed.setBet_type(6);
                }
                if (radioId==listButtonID.get(7)){
                    player_changed.setBet_type(7);
                }

                Editable n = editTextNumber.getText();
                if (n.toString().length()!=0) {
                    Log.d("1st if bet","ok");
                    if ((Integer.parseInt(editTextNumber.getText().toString()) >= 0) && (Integer.parseInt(editTextNumber.getText().toString()) <= 36)) {
                        player_changed.setWheel_number(Integer.parseInt(editTextNumber.getText().toString()));
                        Log.d("2nd if bet",editTextNumber.getText().toString());
                        Log.d("2nd if bet","ok");
                    }
                }
                else{
                    player_changed.setWheel_number(-1);
                }


                player_changed.setHave_bet(true);

                playerDataBase.playerDao().insert(player_changed);

                /*playerDataBase.playerDao().updateBetType(finalPlayerID, radioId);
                playerDataBase.playerDao().updateBetNumber(finalPlayerID, Integer.parseInt(editTextBet.getText().toString()));
                playerDataBase.playerDao().updateHave_Bet(finalPlayerID,true);

                 */

                Log.d("playerID=",Integer.toString(finalPlayerID));
                Log.d("bet_type=",Integer.toString(radioId));
                Log.d("bet_number=",editTextBet.getText().toString());

                Log.d("test1",Integer.toString(playerDataBase.playerDao().getAll().get(finalPlayerID).getBet_type()));
                Log.d("test2",Integer.toString(playerDataBase.playerDao().getAll().get(finalPlayerID).getBet_number()));
                Log.d("test3",Boolean.toString(playerDataBase.playerDao().getAll().get(finalPlayerID).isHave_bet()));



                Intent intent = new Intent(Bet_Layout.this, MainActivity2.class);
                /*intent.putExtra("blackButtonID", findViewById(R.id.blackButton).getId());
                intent.putExtra("redButtonID", findViewById(R.id.redButton).getId());
                intent.putExtra("pairButtonID", findViewById(R.id.pairButton).getId());
                intent.putExtra("impairButtonID", findViewById(R.id.impairButton).getId());
                intent.putExtra("buttonSequence1ID", findViewById(R.id.sequenceButton1).getId());
                intent.putExtra("buttonSequence2ID", findViewById(R.id.sequenceButton2).getId());
                intent.putExtra("buttonSequence3ID", findViewById(R.id.sequenceButton3).getId());
                intent.putExtra("numberChoiceID", findViewById(R.id.numberChoice).getId());

                 */

                if(Integer.parseInt(editTextBet.getText().toString()) > 0){
                    bet_sip=true;
                }
                if (!valuebtwn0and36) {
                    if (radioId!=listButtonID.get(7)) {
                        valuebtwn0and36=true;
                    }
                }

                if (valuebtwn0and36) {
                    startActivity(intent);
                    finish();
                }


            }
        });


    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected option : " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
    public void checkButton2(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        if ((Integer.parseInt(editTextNumber.getText().toString())<-1)|| (Integer.parseInt(editTextNumber.getText().toString())>=36)){
            valuebtwn0and36=false;
            Toast.makeText(this, "Your choosen number is not between 0 and 36",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            valuebtwn0and36=true;
        }
        if (valuebtwn0and36) {
            Toast.makeText(this, "Selected number : " + editTextNumber.getText(),
                    Toast.LENGTH_SHORT).show();
        }
    }



}