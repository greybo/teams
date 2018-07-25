package com.example.sbotlevskyi.teams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sbotlevskyi.teams.entity.Player;
import com.example.sbotlevskyi.teams.entity.Teams;
import com.example.sbotlevskyi.teams.ui.StadiumActivity;
import com.example.sbotlevskyi.teams.utils.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, StadiumActivity.class);
        intent.putExtra(Constants.INTENT_TEAMS, getTeams());
        startActivity(intent);
    }

    private Teams getTeams() {
        Teams teams = new Teams("France", "Belgium", "1", "0",
                new ArrayList<Player>() {{
                    add(new Player("1", "name"));
                    add(new Player("2", "name"));
                    add(new Player("3", "name"));
                    add(new Player("4", "name"));
                    add(new Player("5", "name"));
                    add(new Player("6", "name"));
                    add(new Player("7", "name"));
                    add(new Player("8", "name"));
                    add(new Player("9", "name"));
                    add(new Player("10", "name"));
                    add(new Player("11", "name"));
                }},
                new ArrayList<Player>() {{
                    add(new Player("1", "name"));
                    add(new Player("2", "name"));
                    add(new Player("3", "name"));
                    add(new Player("4", "name"));
                    add(new Player("5", "name"));
                    add(new Player("6", "name"));
                    add(new Player("7", "name"));
                    add(new Player("8", "name"));
                    add(new Player("9", "name"));
                    add(new Player("10", "name"));
                    add(new Player("11", "name"));
                }});
        return teams;
    }
}
