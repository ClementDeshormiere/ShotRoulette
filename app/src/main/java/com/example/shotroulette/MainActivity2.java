package com.example.shotroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private PlayersAdapter myAdapter ;

    private List<Integer> listButtonID = new ArrayList<>();

    private PlayerDataBase playerDataBase;
    private PlayersAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.my_toolbar_players);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Players");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.myRecyclerView);

        Bundle bundle = getIntent().getExtras();
        playerDataBase = PlayerDataBase.getInstance(this);

        if (bundle != null && bundle.containsKey("key")) {
            String playerName = bundle.getString("key");
            Player player = new Player(playerDataBase.playerDao().getAll().size()+1,playerName, 302141);
            playerDataBase.playerDao().insert(player);
        }


        setOnClickListener();
        myAdapter = new PlayersAdapter(playerDataBase.playerDao().getAll(),listener);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myAdapter);

    }

    private void setOnClickListener() {
        listener = new PlayersAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), Bet_Layout.class);
                //intent.putExtra("playerName", playerDataBase.playerDao().getAll().get(position).getName());
                intent.putExtra("playerID",position);
                startActivity(intent);
            }
        };
    }

    public void onClick(View view) {
        Context context = getApplicationContext();
        Class destination = NewPlayer.class;
        Intent intent = new Intent(MainActivity2.this, destination);
        startActivity(intent);
        finish();
    }

    public void onClickStart(View view){
        boolean intent_bool = true;
        for (int i=0;i<playerDataBase.playerDao().getAll().size();i++){
            if(!playerDataBase.playerDao().getAll().get(i).isHave_bet()){
                intent_bool = false;
            }
        }
        if(!intent_bool){
            Toast.makeText(this, "Each player has to bet",
                    Toast.LENGTH_SHORT).show();

        }
        else {
            Context context = getApplicationContext();
            Class destination = Wheel.class;
            Intent intent = new Intent(MainActivity2.this, destination);
            startActivity(intent);
            finish();

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_toolbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.resetBets:
                Context context = getApplicationContext();
                Toast.makeText(context,"Each bet has been reset",Toast.LENGTH_SHORT).show();
                for (int i=0;i<playerDataBase.playerDao().getAll().size();i++){
                    Player player = playerDataBase.playerDao().getAll().get(i);
                    player.setBet_type(-1);
                    player.setBet_number(0);
                    player.setWheel_number(-1);
                    player.setBet_result(0);
                    player.setHave_bet(false);

                    playerDataBase.playerDao().insert(player);
                }
                return true;
            case R.id.resetPlayers:
                Context context2 = getApplicationContext();
                Toast.makeText(context2,"All players have been deleted",Toast.LENGTH_SHORT).show();
                playerDataBase.playerDao().reset(playerDataBase.playerDao().getAll());

                MainActivity2.this.recreate();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}