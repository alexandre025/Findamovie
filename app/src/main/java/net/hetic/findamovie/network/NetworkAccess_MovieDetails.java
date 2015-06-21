package net.hetic.findamovie.network;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;

import java.io.IOException;

/**
 * Created by alexandre on 21/06/15.
 */
public class NetworkAccess_MovieDetails {

    public static final String DETAILS = "details";
    public static final String CREDITS_EXTRA = "creditsExtra";
    public static final String IMAGES_EXTRA = "imagesExtra";

    /**
     * Request casting details for a movie
     * @param id
     */
    public static void requestMovieCredits(Long id) {

        // Build the request with parameters
        String url = UrlBuilder.baseCredits(id);

        if(NetworkAccess.isNetworkAvailable()) {
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
                    try {
                        String jsonData = response.body().string();
                        Log.v(NetworkAccess.TAG, jsonData);
                        if (response.isSuccessful()) {

                            // If response is successful we start a new Intent
                            Intent intent = new Intent(DETAILS);
                            intent.putExtra(CREDITS_EXTRA,jsonData);
                            LocalBroadcastManager.getInstance(MyApp.getContext()).sendBroadcast(intent);

                        } else {
                            //FAIL
                        }
                    } catch (IOException e) {
                        Log.e(NetworkAccess.TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            Toast.makeText(MyApp.getContext(), MyApp.getContext().getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Request more images for a movie
     * @param id
     */
    public static void requestMovieImages(Long id) {

        // Build the request with parameters
        String url = UrlBuilder.baseImages(id);

        if(NetworkAccess.isNetworkAvailable()) {
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
                    try {
                        String jsonData = response.body().string();
                        Log.v(NetworkAccess.TAG, jsonData);
                        if (response.isSuccessful()) {

                            // If response is successful we start a new Intent
                            Intent intent = new Intent(DETAILS);
                            intent.putExtra(IMAGES_EXTRA,jsonData);
                            LocalBroadcastManager.getInstance(MyApp.getContext()).sendBroadcast(intent);

                        } else {
                            //FAIL
                        }
                    } catch (IOException e) {
                        Log.e(NetworkAccess.TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            Toast.makeText(MyApp.getContext(), MyApp.getContext().getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }

    }
}
