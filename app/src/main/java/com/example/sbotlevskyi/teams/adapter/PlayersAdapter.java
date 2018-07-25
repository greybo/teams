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
        View view;
        switch (i) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_player_orange,
                        viewGroup, false);
                return new ViewHolderPlayer(view);
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_player_black,
                        viewGroup, false);
                return new ViewHolderPlayer(view);
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_name_team,
                        viewGroup, false);
                return new ViewHolderName(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (playerList.get(position).getPlayer() != null && playerList.get(position).getTypeTeam() == 1) {
            return 0;
        } else if (playerList.get(position).getPlayer() != null && playerList.get(position).getTypeTeam() == 2) {
            return 1;
        }
        return 2;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListRows player = playerList.get(i);
        viewHolder.bind(player);
    }

    @Override
    public int getItemCount() {
        return playerList == null ? 0 : playerList.size();
    }

    class ViewHolderPlayer extends ViewHolder {
        @BindView(R.id.tv_number_player)
        TextView number;
        @BindView(R.id.tv_name_player)
        TextView name;

        ViewHolderPlayer(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ListRows model) {
            Player player = model.getPlayer();
            number.setText(player.getNumber());
            name.setText(player.getName());
//            if (model.getTypeTeam() == 2) {
//                number.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.background_circle_black));
//            }
        }
    }

    class ViewHolderName extends ViewHolder {
        @BindView(R.id.tv_team_name)
        TextView name;

        ViewHolderName(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ListRows model) {
            name.setText(model.getNameTeam());
        }
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bind(ListRows model);
    }
}
