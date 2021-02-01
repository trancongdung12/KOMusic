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
import android.widget.TextView;
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

    //    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewFragment = inflater.inflate(R.layout.homepage, container, false);
        rcvPlaylist = viewFragment.findViewById(R.id.rycPlaylist);
        //        textView = viewFragment.findViewById(R.id.textView4);
        //
        //        textView.setText("hung" + Account.curId);
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
        helper.insertSong ("Khác biệt to lớn", R.drawable.khacbiettolon, R.raw.khacbiettolon,
                "Trịnh Thăng Bình", "[Liz:]\n" +
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

        helper.insertSong ("Đi về nhà", R.drawable.divenha, R.raw.divenha,
                "Đen & JustaTee", "Đường về nhà là vào tim ta\n" +
                        "Dẫu nắng mưa gần xa\n" +
                        "Thất bát, vang danh\n" +
                        "Nhà vẫn luôn chờ ta\n" +
                        "Đường về nhà là vào tim ta\n" +
                        "Dẫu có muôn trùng qua\n" +
                        "Vận đỗi sao dời\n" +
                        "Nhà vẫn luôn là nhà\n" +
                        "\n" +
                        "Lao vào đời để kiếm cơm, lao vào đời tìm cơ hội\n" +
                        "Tiếng thành thị thường lấp lánh, đêm thành thị thường trơ trọi\n" +
                        "Như mọi đứa trẻ khác, lớn lên muốn đi xa hoài\n" +
                        "Nhà thì vẫn ở yên đó, đợi những đứa con đang ra ngoài\n" +
                        "Bước ra ngoài rồi mới biết, không ở đâu bằng ở nhà\n" +
                        "Bước có gì để mắt trước khi sẵn sàng mở quà\n" +
                        "Không phải là võ sĩ nhưng mà phải giỏi đấu đá\n" +
                        "Nhiều khi kiệt sức chỉ vì gắng giữ mình không xấu xa\n" +
                        "Đôi lúc bỗng thấy đồng cảm với Mai An Tiêm\n" +
                        "Bước chân ra là sóng gió, chỉ có nhà mãi an yên\n" +
                        "Ngoài kia phức tạp như rễ má và dây mơ\n" +
                        "Về nhà để có lúc cho phép mình được ngây thơ\n" +
                        "Mang theo bao náo nức lên chiếc xe này\n" +
                        "Trọn một niềm thao thức suốt những đêm ngày\n" +
                        "Cùng dòng người trên phố mang sắc mai hương đào\n" +
                        "Tìm về nơi ấm êm\n" +
                        "\n" +
                        "Đường về nhà là vào tim ta\n" +
                        "Dẫu nắng mưa gần xa\n" +
                        "Thất bát, vang danh\n" +
                        "Nhà vẫn luôn chờ ta\n" +
                        "Đường về nhà là vào tim ta\n" +
                        "Dẫu có muôn trùng qua\n" +
                        "Vậ đỗi sao dời\n" +
                        "Nhà vẫn luôn là nhà\n" +
                        "\n" +
                        "Về ngôi nhà có góc vườn nhiều chó nhiều gà\n" +
                        "Đám bạn nói con khó chiều\n" +
                        "Và lại thích gió trời hơn gió điều hòa\n" +
                        "Về ăn cơm mẹ nấu, về mặc áo mẹ may\n" +
                        "Về đưa ba ra chợ mua cây đào cây mai về bày\n" +
                        "Cả năm trời làm việc nhiều khi rã rời như cái máy\n" +
                        "Về nhà thấy áp lục nhẹ như bấc thổi cái là bay\n" +
                        "Ấm êm hơn bếp lửa, ngọt bùi hơn lúa non\n" +
                        "Nhà vẫn luôn ở đó, mong chờ những đứa con\n" +
                        "Dẫu cho mưa cho nắng vẫn không bao giờ nề hà\n" +
                        "Hạnh phúc chỉ đơn giản là còn được về nhà\n" +
                        "Hạnh phúc, đi về nhà\n" +
                        "Cô đơn đi về nhà\n" +
                        "Thành công, đi về nhà\n" +
                        "Thất bại, đi về nhà\n" +
                        "Mệ quá, đi về nhà\n" +
                        "Mông lung đi về nhà\n" +
                        "Chênh vênh, đi về nhà\n" +
                        "Không có việc gì vậy thì đi về nhà\n" +
                        "Đi về nhà, đi về nhà,...\n" +
                        "\n" +
                        "Đường về nhà là vào tim ta\n" +
                        "Dẫu nắng mưa gần xa\n" +
                        "Thất bát, vang danh\n" +
                        "Nhà vẫn luôn chờ ta\n" +
                        "Đường về nhà là vào tim ta\n" +
                        "Dẫu có muôn trùng qua\n" +
                        "Vậ đỗi sao dời\n" +
                        "Nhà vẫn luôn là nhà\n" +
                        "\n" +
                        "Nhà có thể lớn có thể nhỏ, có thể không khang trang\n" +
                        "Cha mẹ nào cũng muốn con được sống đàng hoàng\n" +
                        "Lớn lên làm người mong một tương lai xán lạn\n" +
                        "Cặm cụi cả đời không bao giờ thấy than van\n" +
                        "\n" +
                        "Đường về nhà là vào tim ta\n" +
                        "Dẫu nắng mưa gần xa\n" +
                        "Thất bát, vang danh\n" +
                        "Nhà vẫn luôn chờ ta\n" +
                        "Đường về nhà là vào tim ta\n" +
                        "Dẫu có muôn trùng qua\n" +
                        "Vậ đỗi sao dời\n" +
                        "Nhà vẫn luôn là nhà");
        helper.insertSong ("Chỉ có thể là yêu", R.drawable.chicothelayeu,  R.raw.chicothelayeu,
                "Ngô Kiến Huy, Chí Tâm", "Bao năm qua\n" +
                        "Vẫn thầm nghĩ về một người gần nhưng xa\n" +
                        "Bao năm qua\n" +
                        "Vẫn đang ôm thương nhớ ai\n" +
                        "Yêu một người chưa tìm ra lối\n" +
                        "Muốn làm thân nhưng chẳng *** nói\n" +
                        "Nên đành chôn tiếng yêu ấy nay đã 10 năm trôi\n" +
                        "Ở cạnh nhau sao nhiều cảm xúc\n" +
                        "Thật hạnh phúc bên nhau từng chiều\n" +
                        "Ngồi chờ đợi bấy lâu vì chỉ 1 điều\n" +
                        "Chuyện mình đâu chỉ đơn giản là tình yêu\n" +
                        "Mình cũng đâu chỉ là người yêu\n" +
                        "Chợt nhận ra chính đối phương là người mà mình chẳng thể thiếu\n" +
                        "Cuộc đời đâu có mấy khi được gặp nhau\n" +
                        "Và chắc chi sẽ được bền lâu\n" +
                        "Ngần ngại chi hãy yêu đi đừng chỉ nghĩ suy trong đầu\n" +
                        "\n" +
                        "Thích em đã lâu nhưng anh không nói\n" +
                        "Có chút bối rối thế nên thôi\n" +
                        "Nhưng biết đâu em ko từ chối\n" +
                        "Nên anh vẫn đang tìm cơ hội\n" +
                        "\n" +
                        "Em như bông hoa làm anh chill\n" +
                        "Em như câu ca làm anh feel\n" +
                        "Nếu như điều đó em ko tin thì\n" +
                        "Ký hợp đồng luôn anh ko deal (uầy)\n" +
                        "\n" +
                        "Lướt face follow bấm like bắn tim nhắn tin mấy năm trời (mấy năm)\n" +
                        "Nhớ em mỗi đêm, mỗi đêm nhớ em chẳng thể nói nên lời (im luôn)\n" +
                        "Mỗi khi nhắn tin em cục xúc anh vẫn luôn thả icon\n" +
                        "Luôn bên cạnh mọi lúc babe if u call me");
        helper.insertSong ("Dễ Đến Dễ Đi", R.drawable.dedendedi,  R.raw.dedendedi,
                "Quang Hùng", "Vài câu hứa em nói sẽ chẳng bao giờ xa anh\n" +
                        "Từ đầu môi mềm thắm say đắm cho ai mơ màng\n" +
                        "Cứ ngỡ bên nhau, nồng ấm bao lâu, dịu dàng em khẽ trao\n" +
                        "Vậy tại sao em gieo vào tim này xót xa\n" +
                        "Chỉ vì thứ tha bao lâu anh cứ như người thứ ba\n" +
                        "Đừng nhìn anh đôi mắt cất giấu ở phía sau\n" +
                        "Những lừa dối đã bấy lâu em ơi...\n" +
                        "Vội vàng em đến, đến rồi đi\n" +
                        "Để làm chi, để rồi gieo vẫn vương trong lòng anh\n" +
                        "Bao sầu bi, khi biệt ly\n" +
                        "Hoen bờ mi đứng trong người đi\n" +
                        "Nhìn em quay bước theo ai nơi hoàng hôn khuất bóng đêm phai\n" +
                        "Phủ lâu nay vắng bóng hình ai, anh ngồi đây\n" +
                        "Ly rượu say đắng hơi men nào cay, mưa phùn bay\n" +
                        "Tình trao như gió heo mây\n" +
                        "Câu thề xưa vỡ trong chiều mưa.\n" +
                        "Có lẽ giờ em đã quên rồi\n" +
                        "Bao nhung nhớ phôi phai\n" +
                        "Trôi dần theo những ngày tháng mình anh ngồi\n" +
                        "Ôm đau giữa mây trời\n" +
                        "Tình đời anh quá chịu nhiều gió sương\n" +
                        "Một lòng thương nhớ còn đầy vấn vương\n" +
                        "Trong tim anh là đắng cay\n" +
                        "Trên tay anh là trắng tay\n" +
                        "Thuyền mãi rời xa bờ chốn đây\n" +
                        "Lòng nào dám mơ\n" +
                        "Tình này có em lâu dài\n" +
                        "Ừ là vì anh đã dại khờ ngu ngơ\n" +
                        "Để giờ người quay bước vội vàng thờ ơ\n" +
                        "Còn đâu rồi");
        helper.insertSong ("Nàng Thơ", R.drawable.nangtho,  R.raw.nangtho,
                "Hoàng Dũng", "Em, ngày em đánh rơi nụ cười vào anh\n" +
                        "Có nghĩ sau này em sẽ chờ\n" +
                        "Và vô tư cho đi hết những ngây thơ\n" +
                        "Anh, một người hát mãi những điều mong manh\n" +
                        "Lang thang tìm niềm vui đã lỡ\n" +
                        "Chẳng buồn dặn lòng quên hết những chơ vơ\n" +
                        "\n" +
                        "Ta yêu nhau bằng nỗi nhớ chưa khô trên những bức thư\n" +
                        "Ta đâu bao giờ có lỗi khi không nghe tim chối từ\n" +
                        "Chỉ tiếc rằng\n" +
                        "\n" +
                        "Em không là nàng thơ\n" +
                        "Anh cũng không còn là nhạc sĩ mộng mơ\n" +
                        "Tình này nhẹ như gió\n" +
                        "Lại trĩu lên tim ta những vết hằn\n" +
                        "Tiếng yêu này mỏng manh\n" +
                        "Giờ tan vỡ, thôi cũng đành\n" +
                        "Xếp riêng những ngày tháng hồn nhiên\n" +
                        "Trả lại...\n" +
                        "\n" +
                        "Mai, rồi em sẽ quên ngày mình khờ dại\n" +
                        "Mong em kỷ niệm này cất lại\n" +
                        "Mong em ngày buồn thôi ướt đẫm trên vai\n" +
                        "Mai, ngày em sải bước bên đời thênh thang\n" +
                        "Chỉ cần một điều em hãy nhớ\n" +
                        "\n" +
                        "Có một người từng yêu em tha thiết vô bờ\n" +
                        "\n" +
                        "Em không là nàng thơ\n" +
                        "Anh cũng không còn là nhạc sĩ mộng mơ\n" +
                        "Tình này nhẹ như gió\n" +
                        "Lại trĩu lên tim ta những vết hằn\n" +
                        "Tiếng yêu này mỏng manh\n" +
                        "Giờ tan vỡ, thôi cũng đành\n" +
                        "Xếp riêng những ngày tháng hồn nhiên\n" +
                        "Trả hết cho em");

    }
}