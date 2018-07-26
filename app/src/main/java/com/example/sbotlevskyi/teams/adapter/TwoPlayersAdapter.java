package com.example.sbotlevskyi.teams.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sbotlevskyi.teams.R;
import com.example.sbotlevskyi.teams.entity.Player;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwoPlayersAdapter extends RecyclerView.Adapter<TwoPlayersAdapter.ViewHolder> {
    private ArrayList<Player> players1;
    private ArrayList<Player> players2;

    public TwoPlayersAdapter(ArrayList<Player> players1, ArrayList<Player> players2) {
        this.players1 = players1;
        this.players2 = players2;
    }

    @NonNull
    @Override
    public TwoPlayersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_two_players, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoPlayersAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        int count = players1.size();
        if (players2.size() > count) {
            count = players2.size();
        }
        return count;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_number_player_1)
        TextView numberPlayer1;
        @BindView(R.id.tv_number_player_2)
        TextView numberPlayer2;
        @BindView(R.id.tv_name_player_1)
        TextView namePlayer1;
        @BindView(R.id.tv_name_player_2)
        TextView namePlayer2;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(int i) {
            if (i < players1.size()) {
                Player player1 = players1.get(i);
                numberPlayer1.setText(player1.getNumber());
                namePlayer1.setText(player1.getName());
            }
            if (i < players2.size()) {
                Player player2 = players2.get(i);
                numberPlayer2.setText(player2.getNumber());
                namePlayer2.setText(player2.getName());
            }
        }
    }
}
