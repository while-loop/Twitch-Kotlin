package science.anthonyalves.twitchkotlin.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.salomonbrys.kotson.get
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.twitch_data_view.view.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import science.anthonyalves.twitchkotlin.R
import science.anthonyalves.twitchkotlin.adapters.StreamAdapter
import science.anthonyalves.twitchkotlin.api.TwitchApiClient
import science.anthonyalves.twitchkotlin.models.Stream
import java.util.*


class StreamFragment : Fragment() {
    lateinit var mView: View
    lateinit var mAdapter: StreamAdapter

    var isDownloading: Boolean = false
    val ITEMS_PER_PAGE: Int = 20
    var PAGE = 0


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.twitch_data_view, container, false)
        initRecyclerView()
        return mView;
    }

    private fun initRecyclerView() {
        mView.mSwipeRefreshLayout.setOnRefreshListener { refreshData() }
        mView.mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        mView.mRecyclerView.layoutManager = LinearLayoutManager(context)
        mView.mRecyclerView.setHasFixedSize(true);
        mAdapter = StreamAdapter()
        mView.mRecyclerView.adapter = mAdapter
        refreshData()
    }

    fun refreshData() {
        Log.d("Stream", "refreshData: gg")
        if (isDownloading) return

        isDownloading = true
        val call = TwitchApiClient.getTwitchService()?.getStreams(ITEMS_PER_PAGE, PAGE * ITEMS_PER_PAGE)
        call?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (!response.isSuccessful) {
                    call.enqueue(this)
                } else {
                    mAdapter.swapData(responseToList(response.body().string()))
                    PAGE = 0
                }
                finishRefresh()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
                finishRefresh()
            }
        });
    }

    private fun responseToList(string: String?): ArrayList<Stream> {
        val returnList = ArrayList<Stream>()
        val obj: JsonObject = JsonParser().parse(string).asJsonObject
        for (stream in obj["streams"].asJsonArray) {
            returnList.add(Stream(stream["preview"]["large"].asString,
                    stream["viewers"].asInt,
                    stream["channel"]["display_name"].asString,
                    stream["channel"]["status"].asString,
                    stream["channel"]["game"].asString))
        }
        return returnList
    }

    private fun finishRefresh() {
        mView.mSwipeRefreshLayout.isRefreshing = false
        isDownloading = false
    }
}