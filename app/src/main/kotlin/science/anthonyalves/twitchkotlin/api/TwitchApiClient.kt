package science.anthonyalves.twitchkotlin.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

object TwitchApiClient {
    val twitchService: TwitchService by lazy {
        val restAdapter = Retrofit.Builder().baseUrl("https://api.twitch.tv/kraken/").build()
        restAdapter.create(TwitchService::class.java)
    }


    interface TwitchService {
        @GET("streams")
        fun getStreams(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<ResponseBody>

        @GET("games/top")
        fun getGames(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<ResponseBody>
    }
}
