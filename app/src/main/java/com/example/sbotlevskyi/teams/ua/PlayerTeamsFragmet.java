package com.example.sbotlevskyi.teams.ua;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sbotlevskyi.teams.adapter.PlayersAdapter;
import com.example.sbotlevskyi.teams.R;
import com.example.sbotlevskyi.teams.entity.Player;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerTeamsFragmet extends Fragment {
    @BindView(R.id.rv_team1)
    RecyclerView recyclerViewTeams;

    private static final String ARG_PLAYERS_TEAM1 = "players1";
    private static final String ARG_PLAYERS_TEAM2 = "players2";
    private ArrayList<Player> players1;
    private ArrayList<Player> players2;
    private PlayersAdapter playersAdapter;

    public PlayerTeamsFragmet() {
    }

    public static PlayerTeamsFragmet newInstance(ArrayList<Player> players1, ArrayList<Player> players2) {
        PlayerTeamsFragmet fragment = new PlayerTeamsFragmet();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PLAYERS_TEAM1, players1);
        args.putParcelableArrayList(ARG_PLAYERS_TEAM2, players2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_teams, container, false);
        ButterKnife.bind(this, rootView);
        players1 = getArguments().getParcelableArrayList(ARG_PLAYERS_TEAM1);
        players2 = getArguments().getParcelableArrayList(ARG_PLAYERS_TEAM2);
        createAdapter(players1);
        return rootView;
    }

    private void createAdapter(ArrayList<Player> players1) {
        playersAdapter = new PlayersAdapter(players1);
        recyclerViewTeams.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTeams.setAdapter(playersAdapter);
    }
}
