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

    @BindView(R.id.football_field_layout_team_1)
    LinearLayout fieldLayout1;
    @BindView(R.id.football_field_layout_team_2)
    LinearLayout fieldLayout2;
    @BindView(R.id.rv_two_teams_players)
    RecyclerView twoTeamsPlayersRecycler;
    @BindView(R.id.tv_name_team_1)
    TextView nameTeam1;
    @BindView(R.id.tv_name_team_2)
    TextView nameTeam2;

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
        for (int i : teams.getSequenceTeam1()) {
            fieldLayout1.addView(getView(i, 1, playerStack));
        }

        playerStack = TeamUtils.toStack(teams.getPlayersTeam2());
        for (int i : TeamUtils.arrayRevert(teams.getSequenceTeam2())) {
            fieldLayout2.addView(getView(i, 2, playerStack));
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
