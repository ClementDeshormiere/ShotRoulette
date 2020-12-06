package com.example.shotroulette;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
interface PlayerDao {
    @Insert(onConflict = REPLACE)
    void insert (Player player);

    @Delete
    void delete(Player player);

    @Delete
    void reset(List<Player> playerList);

    @Query("UPDATE player_table SET bet_type = :setBet_type WHERE ID = :setID")
    void updateBetType(int setID, int setBet_type);

    @Query("UPDATE player_table SET bet_number = :setBet_number WHERE ID = :setID")
    void updateBetNumber(int setID, int setBet_number);

    @Query("UPDATE player_table SET have_bet = :setHave_Bet WHERE ID = :setID")
    void updateHave_Bet(int setID, boolean setHave_Bet);

    @Query("SELECT * FROM player_table")
    List<Player> getAll();

}