package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Profile extends Fragment {
    View viewFragment;
    private TextView txtName;
    private TextView txtNumSong;
    private RecyclerView rcvRecentSong;
    ArrayList<Playlist> arrayList;
    Account account;
    DB helper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.activity_profile, container, false);
        helper = new DB(getActivity());

        txtName = viewFragment.findViewById(R.id.txt_name);
        txtNumSong = viewFragment.findViewById(R.id.num_liked_song);
        account = helper.getAccount(Account.curId);
        txtName.setText(account.getFirstName()+" "+ account.getLastName()+" ("+ account.getNickname()+")");
        txtNumSong.setText(getLength()+" ");

        return viewFragment;
    }
    private int getLength() {
        List<Song> list = helper.getAllCollection(Account.curId);
        return list.size();
    }
}