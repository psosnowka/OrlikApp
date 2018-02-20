package com.example.pawel.orlikapp.ui.menu.bookingdetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.utils.Logs;

import java.util.List;

public class PlayerListFragmet extends Fragment {
    private ListView listView;
    private ImageButton settingsBtn;
    private CustomAdapter customAdapter;
    private boolean leader;
    private String leaderName;
    private List<Player> list;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_list_fragmet, container, false);
        init(view);
        Bundle bundle = getArguments();
        leaderName = (String) bundle.getSerializable("leaderName");
        leader = (boolean) bundle.getSerializable("isLeader");
        Logs.d("PlayerListFragment", "IsLeader: " + leader);
        list = (List<Player>) bundle.getSerializable("player_list");
        customAdapter = new CustomAdapter(list, getContext(), leader, leaderName, new CustomAdapter.DataChangedListener() {
            @Override
            public void dataChanged() {
                customAdapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(customAdapter);
        onCllick();
        setLeaderOptions();
        return view;
    }

    private void init(View view) {
        listView = (ListView) view.findViewById(R.id.playerList);
        settingsBtn = (ImageButton) view.findViewById(R.id.settingsBtn);
    }

    private void setLeaderOptions() {
        if (leader) {
            settingsBtn.setVisibility(View.VISIBLE);
        }
    }

    private void onCllick() {
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    View view1 = listView.getRootView();
                    ImageButton deleteBtn = (ImageButton) view1.findViewById(R.id.deleteBtn);
//                    deleteBtn.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logs.d("PlayerListFragment", "Brak graczy na liscie");
                }

            }
        });

    }
}