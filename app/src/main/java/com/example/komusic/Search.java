package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Search extends Fragment {
    View viewFragment;
    private RecyclerView rcvRecentSong;
    DB helper;
    TextView btnSearch;
    EditText txtSearch;

    GridLayout gridSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.activity_search, container, false);
        helper = new DB(getActivity());
        gridSearch = viewFragment.findViewById(R.id.GridLayout1);
        btnSearch = viewFragment.findViewById(R.id.search);
        txtSearch = viewFragment.findViewById(R.id.txt_search);
        rcvRecentSong = viewFragment.findViewById(R.id.rycSearch);

        txtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() >= 0){
                    gridSearch.setVisibility(View.GONE);
                    rcvRecentSong.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    rcvRecentSong.setItemAnimator(new DefaultItemAnimator());
                    CollectionAdapter adapterSong = new CollectionAdapter( getListSong(txtSearch.getText().toString()), getActivity());
                    rcvRecentSong.setAdapter(adapterSong);
                    rcvRecentSong.setVisibility(View.VISIBLE);
                }else {
                    gridSearch.setVisibility(View.VISIBLE);
                    rcvRecentSong.setVisibility(View.GONE);
                }

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                gridSearch.setVisibility(View.GONE);
                rcvRecentSong.setVisibility(View.VISIBLE);
            }

        });



        return viewFragment;
    }
    private List<Song> getListSong(String input) {
        List<Song> list = helper.getSearchSong(input);
        return list;
    }
}