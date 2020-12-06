package com.example.shotroulette;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Result_Adapter extends RecyclerView.Adapter<Result_Adapter.MyViewHolder> {

    private List<Player> playerList;
    private Activity context;
    private PlayerDataBase playerDataBase ;
    private RecyclerViewClickListener2 listener;

    public Result_Adapter(List<Player> playerList, RecyclerViewClickListener2 listener) {
        this.playerList = playerList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.result_cell, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        playerDataBase = PlayerDataBase.getInstance(context);
        holder.display(playerDataBase.playerDao().getAll().get(position));

    }

    @Override
    public int getItemCount() {
        if (playerList==null){
            return 0;
        } else {return playerList.size(); }

    }

    public interface RecyclerViewClickListener2{
        void onClick(View v, int position);

    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mPlayerName;
        private TextView mSipsDisplay;
        private ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            mPlayerName = itemView.findViewById(R.id.playerName);
            mSipsDisplay = itemView.findViewById(R.id.textSipsDisplay);
            itemView.setOnClickListener(this);


        }

        void display(Player player){
            mPlayerName.setText(player.getName());
            int sip_number = player.getBet_result();
            Log.d("nombre de gorgées = ", Integer.toString(sip_number));
            mSipsDisplay.setText("You give " + sip_number + " sips");
            if (player.isHave_drunk()){
                constraintLayout.setBackgroundResource(R.color.green);
            }

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

}
