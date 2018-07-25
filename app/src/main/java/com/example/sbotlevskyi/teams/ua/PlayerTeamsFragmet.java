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
import com.example.sbotlevskyi.teams.entity.ListRows;
import com.example.sbotlevskyi.teams.entity.Player;
import com.example.sbotlevskyi.teams.entity.Teams;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerTeamsFragmet extends Fragment {
    @BindView(R.id.rv_team1)
    RecyclerView recyclerViewTeams;

    private static final String ARG_PLAYERS_TEAM = "team";
    private ArrayList<ListRows> listRows;
    private Teams teams;
    private PlayersAdapter playersAdapter;

    public PlayerTeamsFragmet() {
    }

    public static PlayerTeamsFragmet newInstance(Teams teams) {
        PlayerTeamsFragmet fragment = new PlayerTeamsFragmet();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PLAYERS_TEAM, teams);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_teams, container, false);
        ButterKnife.bind(this, rootView);
        teams = getArguments().getParcelable(ARG_PLAYERS_TEAM);
        listRows = new ArrayList<>();
        createAdapter();
        return rootView;
    }

    private void createAdapter() {
        playersAdapter = new PlayersAdapter(getListRows());
        recyclerViewTeams.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTeams.setAdapter(playersAdapter);
    }

    private ArrayList<ListRows> getListRows() {
        addToList(teams.playersTeam1, teams.nameTeam1,1);
        addToList(teams.playersTeam2, teams.nameTeam2,2);
        return listRows;
    }

    private void addToList(ArrayList<Player> players, String teamName,int type) {
        listRows.add(new ListRows(teamName));
        for (Player p : players) {
            listRows.add(new ListRows(p,type));
        }
    }

}
