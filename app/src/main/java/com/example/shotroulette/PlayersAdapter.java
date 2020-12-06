package com.example.shotroulette;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.MyViewHolder> {

    private List<Player> playerList;
    private Activity context;
    private PlayerDataBase playerDataBase;

    private RecyclerViewClickListener listener;

    public PlayersAdapter(List<Player> playerList, RecyclerViewClickListener listener) {
        this.playerList = playerList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.player_cell, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.display(playerList.get(position));
        playerDataBase = PlayerDataBase.getInstance(context);

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player p = playerList.get(holder.getAdapterPosition());
                playerDataBase.playerDao().delete(p);

                int position = holder.getAdapterPosition();

                playerList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,playerList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (playerList==null){
            return 0;
        } else {return playerList.size(); }

    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mPlayerName;
        private ImageButton removeButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mPlayerName = itemView.findViewById(R.id.playerName);
            removeButton = itemView.findViewById(R.id.removeButton);
            itemView.setOnClickListener(this);

        }

        void display(Player player){
            mPlayerName.setText(player.getName());

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

}
