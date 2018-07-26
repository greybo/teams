package com.example.sbotlevskyi.teams.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sbotlevskyi.teams.R;
import com.example.sbotlevskyi.teams.entity.Teams;
import com.example.sbotlevskyi.teams.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StadiumActivity extends AppCompatActivity {
    private static final String TAG = "StadiumActivity";
    @BindView(R.id.tv_name_team1)
    TextView titleNameTeam1;
    @BindView(R.id.tv_name_team2)
    TextView titleNameTeam2;
    @BindView(R.id.tv_score_team1)
    TextView titleScoreTeam1;
    @BindView(R.id.tv_score_team2)
    TextView titleScoreTeam2;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.icon_flag_team_1)
    ImageView iconFlag1;
    @BindView(R.id.icon_flag_team_2)
    ImageView iconFlag2;

    private Teams teams;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int appbarHeight;
    private int scoreHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium);
        ButterKnife.bind(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        teams = getIntent().getParcelableExtra(Constants.INTENT_TEAMS);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                appbarHeight = appBarLayout.getTotalScrollRange();
                double coefficient = ((double) (appbarHeight + verticalOffset) / appbarHeight);

                Log.i(TAG, "onOffsetChanged:  appbarHeight=" + (appbarHeight + verticalOffset) + "  coefficient=" + coefficient);
                iconFlag1.setScaleX((float) coefficient);
                iconFlag1.setScaleY((float) coefficient);
                iconFlag2.setScaleX((float) coefficient);
                iconFlag2.setScaleY((float) coefficient);
            }
        });
        fieldTitleScore();
    }

    private void fieldTitleScore() {
        titleNameTeam1.setText(teams.getNameTeam1());
        titleNameTeam2.setText(teams.getNameTeam2());
        titleScoreTeam1.setText(teams.getScoreTeam1());
        titleScoreTeam2.setText(teams.getScoreTeam2());
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
