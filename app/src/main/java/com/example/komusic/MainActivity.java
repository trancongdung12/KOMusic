package com.example.komusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Fragment {
    View viewFragment;
    private RecyclerView rcvPlaylist;
    private RecyclerView rcvRecentSong;
    ArrayList<Playlist> arrayList;
    ArrayList<Song> arrayListSong;
    DB helper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        viewFragment = inflater.inflate(R.layout.homepage, container, false);
        rcvPlaylist = viewFragment.findViewById(R.id.rycPlaylist);

        helper = new DB(getActivity());
        //fix data for database
        if(getListSong().size()<=0){
            renderSong();
        }
        rcvPlaylist = viewFragment.findViewById(R.id.rycPlaylist);
        int banner[] = {R.drawable.banner1,R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5};
        arrayList = new ArrayList<>();


        rcvPlaylist.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rcvPlaylist.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < banner.length; i++) {
            Playlist itemModel = new Playlist();
            itemModel.setResourceId(banner[i]);

            //add in array list
            arrayList.add(itemModel);
        }

        PlaylistAdapter adapter = new PlaylistAdapter(getActivity(), arrayList);
        rcvPlaylist.setAdapter(adapter);


        //recent song

        rcvRecentSong = viewFragment.findViewById(R.id.rycRecentSong);
        rcvRecentSong.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rcvRecentSong.setItemAnimator(new DefaultItemAnimator());
        SongAdapter adapterSong = new SongAdapter( getListSong(), getActivity());
        rcvRecentSong.setAdapter(adapterSong);

        //made for you
        rcvRecentSong = viewFragment.findViewById(R.id.rycMadeForYou);
        rcvRecentSong.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rcvRecentSong.setItemAnimator(new DefaultItemAnimator());
        SongAdapter adapterForYou = new SongAdapter( getListSong(), getActivity());
        rcvRecentSong.setAdapter(adapterForYou);

        //singer
        rcvRecentSong = viewFragment.findViewById(R.id.rycSinger);
        rcvRecentSong.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rcvRecentSong.setItemAnimator(new DefaultItemAnimator());

        SongAdapter adapterSinger = new SongAdapter( getListSong(), getActivity());
        rcvRecentSong.setAdapter(adapterSinger);


        //Demonstrate

        ImageView next = (ImageView) viewFragment.findViewById(R.id.btn_temp);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Player.class);
                startActivityForResult(myIntent, 0);
            }

        });
        return viewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private List<Song> getListSong() {
        List<Song> list = helper.getAllSong();
        return list;
    }

        private void renderSong (){
        helper.insertSong ("Khác biệt to lớn", R.drawable.khacbiettolon, "Ngày hạnh phúc",
                "phúc", "[Liz:]\n" +
                        "Đoạn đường mới người bước bên anh không là em nữa\n" +
                        "Anh đã cảm thấy thoải mái chút nào chưa\n" +
                        "Từng lời hứa mình nói khi xưa không cần thiết nữa\n" +
                        "Giờ đây niềm đau đã quá thừa.\n" +
                        "\n" +
                        "[TTP:]\n" +
                        "Có thể giấu vết thương trong lòng\n" +
                        "Nhưng chẳng thể giấu hết nỗi thất vọng\n" +
                        "Có thể giấu hết đi chuyện tình chúng mình\n" +
                        "Nhưng chẳng thể bỏ đi một mối tình\n" +
                        "Ngày em nói ở bên cạnh nhau quá nhiều mệt mỏi\n" +
                        "Anh đã cố gắng níu kéo nhưng lại thôi\n" +
                        "Ngày em nói giờ chúng ta có khác biệt quá lớn\n" +
                        "Là do em cần ai đó hơn\n" +
                        "\n" +
                        "[Liz:]\n" +
                        "Đoạn đường mới người bước bên anh không là em nữa\n" +
                        "Anh đã cảm thấy thoải mái chút nào chưa\n" +
                        "Từng lời hứa mình nói khi xưa không cần thiết nữa\n" +
                        "Giờ đây niềm đau đã quá thừa\n" +
                        "\n" +
                        "[TTP-Rap:]\n" +
                        "Bỏ thêm muối nước sẽ thêm mặn\n" +
                        "Thêm niềm tin thì tình sẽ thêm sâu nặng\n" +
                        "Nhưng muốn cùng tôi oh oh\n" +
                        "Nên người say no no\n" +
                        "Nếu thiếu cá biển vẫn sẽ mặn\n" +
                        "Chút sâu lắng cho tình tôi lên ngôi\n" +
                        "Kết thúc khác nhau vì câu nói\n" +
                        "\n" +
                        "[TTP:]\n" +
                        "Ngày em nói ở bên cạnh nhau quá nhiều mệt mỏi\n" +
                        "Anh đã cố gắng níu kéo nhưng lại thôi\n" +
                        "Ngày em nói giờ chúng ta có khác biệt quá lớn\n" +
                        "Là do em cần ai đó hơn\n" +
                        "\n" +
                        "[Liz:]\n" +
                        "Đoạn đường mới người bước bên anh không là em nữa\n" +
                        "Anh đã cảm thấy thoải mái chút nào chưa\n" +
                        "Từng lời hứa mình nói khi xưa không cần thiết nữa\n" +
                        "Giờ đây niềm đau đã quá thừa\n" +
                        "\n" +
                        "Đoạn đường mới người bước bên anh không là em nữa\n" +
                        "Anh đã cảm thấy thoải mái chút nào chưa\n" +
                        "Từng lời hứa mình nói khi xưa không cần thiết nữa\n" +
                        "Giờ đây niềm đau đã quá thừa\n" +
                        "\n" +
                        "[TTP:]\n" +
                        "Ngày em nói ở bên cạnh nhau quá nhiều mệt mỏi\n" +
                        "Anh đã cố gắng níu kéo nhưng lại thôi\n" +
                        "Ngày em nói giờ chúng ta có khác biệt quá lớn\n" +
                        "Là do em cần ai đó hơn");

        helper.insertSong ("Ngày hạnh", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày phúc", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày", R.drawable.song, "Ngày hạnh phúc",
                "phúc", "Ngày hạnh phúc huhuhuhu");
        helper.insertSong ("Ngày hạnh", R.drawable.song, "Ngày hạnh phúc",
                "hanh", "Ngày hạnh phúc huhuhuhu");
    }
}