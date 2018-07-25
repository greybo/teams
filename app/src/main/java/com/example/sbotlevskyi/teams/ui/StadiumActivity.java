package com.example.sbotlevskyi.teams.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sbotlevskyi.teams.R;
import com.example.sbotlevskyi.teams.entity.Teams;
import com.example.sbotlevskyi.teams.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StadiumActivity extends AppCompatActivity {
    @BindView(R.id.tv_team1)
    TextView titleTeam1;
    @BindView(R.id.tv_team2)
    TextView titleTeam2;

    private Teams teams;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        teams = getIntent().getParcelableExtra(Constants.INTENT_TEAMS);
        titleTeam1.setText(String.format("%s %s : %s %s", teams.nameTeam1, teams.scoreTeam1,
                teams.scoreTeam2, teams.nameTeam2));
//        titleTeam1.setText(teams.nameTeam1 + ' ' + teams.scoreTeam1+" :");
//        titleTeam2.setText(" "+teams.scoreTeam2+ ' ' +teams.nameTeam2);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PlayerTeamsFragment.newInstance(teams);
                default:
                    return LocationPlayersFragment.newInstance(teams);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
