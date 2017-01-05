package science.anthonyalves.twitchkotlin;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import science.anthonyalves.twitchkotlin.adapters.StreamAdapter;
import science.anthonyalves.twitchkotlin.api.TwitchApiClient;
import science.anthonyalves.twitchkotlin.models.Stream;

class SSS {
    @SerializedName("test_noob")
    String noob;

    public SSS(String noob) {
        this.noob = noob;
    }
}

public class test {

    public static void main(String[] args) {
        RecyclerView c = new RecyclerView(null);
        final StreamAdapter s = new StreamAdapter();
        c.setAdapter(s);

    }
}
