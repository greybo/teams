package com.example.sbotlevskyi.teams.ua;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sbotlevskyi.teams.R;

public class LocationPlayersFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "arg";

    public LocationPlayersFragment() {
    }

    public static LocationPlayersFragment newInstance(int sectionNumber) {
        LocationPlayersFragment fragment = new LocationPlayersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_players, container, false);

        return rootView;
    }
}
