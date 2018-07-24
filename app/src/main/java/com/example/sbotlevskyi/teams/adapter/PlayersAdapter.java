package com.example.sbotlevskyi.teams.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sbotlevskyi.teams.R;
import com.example.sbotlevskyi.teams.entity.ListRows;
import com.example.sbotlevskyi.teams.entity.Player;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    private ArrayList<ListRows> playerList;

    public PlayersAdapter(ArrayList<ListRows> playerList) {

        this.playerList = playerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        switch (i) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_player,
                        viewGroup, false);
                break;
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_name_team,
                        viewGroup, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (playerList.get(position).getPlayer() != null) {
            return 0;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Player player = playerList.get(i).getPlayer();
        viewHolder.bind(player);
    }

    @Override
    public int getItemCount() {
        return playerList == null ? 0 : playerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_number_player)
        TextView number;
        @BindView(R.id.tv_name_player)
        TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Player model) {
            number.setText(model.number);
            name.setText(model.name);
        }
    }
}
