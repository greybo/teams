package com.example.sbotlevskyi.teams.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sbotlevskyi.teams.R;
import com.example.sbotlevskyi.teams.adapter.TwoPlayersAdapter;
import com.example.sbotlevskyi.teams.entity.Player;
import com.example.sbotlevskyi.teams.entity.Teams;
import com.example.sbotlevskyi.teams.utils.TeamUtils;

import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationPlayersFragment extends Fragment {

    @BindView(R.id.football_field_layout)
    LinearLayout fieldLayout;
    @BindView(R.id.rv_two_teams_players)
    RecyclerView twoTeamsPlayersRecycler;
    @BindView(R.id.tv_name_team_1)
    TextView nameTeam1;
    @BindView(R.id.tv_name_team_2)
    TextView nameTeam2;

    private int[] sequence = {1, 4, 2, 3, 1};
    private int[] sequenceBack = {1, 3, 2, 4, 1};
    private static final String ARG_PLAYERS_TEAM = "team";
    private Teams teams;

    public LocationPlayersFragment() {
    }

    public static LocationPlayersFragment newInstance(Teams teams) {
        LocationPlayersFragment fragment = new LocationPlayersFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PLAYERS_TEAM, teams);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_players, container, false);
        ButterKnife.bind(this, rootView);
        teams = getArguments().getParcelable(ARG_PLAYERS_TEAM);
        nameTeam1.setText(teams.getNameTeam1());
        nameTeam2.setText(teams.getNameTeam2());
        fillFootballField();
        createAdapter();
        return rootView;
    }

    private void createAdapter() {
        TwoPlayersAdapter adapter = new TwoPlayersAdapter(teams.getPlayersTeam1(), teams.getPlayersTeam2());
        twoTeamsPlayersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        twoTeamsPlayersRecycler.setAdapter(adapter);
    }

    private void fillFootballField() {
        Stack<Player> playerStack;
        playerStack = TeamUtils.toStackRevert(teams.getPlayersTeam1());
        getPlayerItems(playerStack, 1);
        playerStack = TeamUtils.toStack(teams.getPlayersTeam2());
        getPlayerItems(playerStack, 2);
    }

    private void getPlayerItems(Stack<Player> playerStack, int typeTeam) {
        int[] arr;
        if (typeTeam == 1) {
            arr = sequence;
        } else {
            arr = sequenceBack;
        }

        for (int i : arr) {
            fieldLayout.addView(getView(i, typeTeam, playerStack));
        }
    }

    private View getView(int countPlayers, int typeTeam, Stack<Player> playerStack) {
        View view;
        Player player;
        LinearLayout layoutRow = new LinearLayout(getContext());
        layoutRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        layoutRow.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < countPlayers; i++) {
            if (!playerStack.empty()) {
                player = playerStack.pop();

                if (player != null && player.isGoalkeeper()) {
                    view = LayoutInflater
                            .from(getContext())
                            .inflate(R.layout.item_player_circle_field_white, layoutRow, false);
                } else {
                    if (typeTeam == 1) {
                        view = LayoutInflater
                                .from(getContext())
                                .inflate(R.layout.item_player_circle_field_orange, layoutRow, false);
                    } else {
                        view = LayoutInflater
                                .from(getContext())
                                .inflate(R.layout.item_player_circle_field_black, layoutRow, false);
                    }
                }

                ((TextView) view.findViewById(R.id.tv_circle_number)).setText(player.getNumber());
                ((TextView) view.findViewById(R.id.tv_circle_name)).setText(player.getName());
                layoutRow.addView(view, i);
            }

        }
        return layoutRow;
    }


}
