package net.hetic.findamovie.network;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alexandre on 30/05/15.
 */
public class NetworkAcces {

    public static final String TAG = NetworkAcces.class.getSimpleName();

    public static void requestMovies() {

        String apiUrl = "http://api.themoviedb.org/3/discover/movie";
        String apiKey = "c1ac741d5dd740f9861e794c5363b0c2";

        String url = apiUrl+"?api_key="+apiKey;

        System.out.println(url);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try{

                    if (response.isSuccessful()){
                        Log.v(TAG, response.body().string());
                    }
                } catch (IOException e){
                    Log.e(TAG, "Exception caught: ", e);
                }
            }
        });


    }
}