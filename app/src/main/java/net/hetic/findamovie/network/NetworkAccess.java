package net.hetic.findamovie.network;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.hetic.findamovie.ui.activities.DisplayResults;
import net.hetic.findamovie.ui.activities.FindMovie;
import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;

import java.io.IOException;

/**
 * Created by alexandre on 30/05/15.
 */
public class NetworkAccess {

    public static final String TAG = NetworkAccess.class.getSimpleName();
    public static String url;
    public static String jsonData;
    private static String apiKey = "70890ed92e2d332f35ea0cd41086c921";

    /**
     * Preload list of categories witch will be displayed in FindMovie activity
     */
    public static void requestGenres() {

        String apiUrl = "http://api.themoviedb.org/3/genre/movie/list";

        String language = MyApp.getLanguage();

        url = apiUrl + "?api_key=" + apiKey + "&language=" + language;

        System.out.println(url);

        if (isNetworkAvailable()) {
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
                        jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {

                            // If response is successful we start a new Intent
                            Intent intent = new Intent(MyApp.getInstance().getApplicationContext(), FindMovie.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("REQUESTED_GENRES", jsonData);
                            MyApp.getContext().startActivity(intent);

                        } else {
                            //FAIL
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        } else {
            Toast.makeText(MyApp.getContext(), MyApp.getContext().getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Build and request the movie list
     * @param genres
     */
    public static void requestMovies(String genres) {

        String apiUrl = "http://api.themoviedb.org/3/discover/movie";

        // Get current device's language
        String language = MyApp.getLanguage();

        // Build the request with parameters
        url = apiUrl+"?api_key="+apiKey+"&"+genres+"&language="+language;

        if(isNetworkAvailable()) {
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
                        jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {

                            // If response is successful we start a new Intent
                            Intent intent = new Intent(MyApp.getInstance().getApplicationContext(), DisplayResults.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("REQUESTED_MOVIES", jsonData);
                            intent.putExtra("LAST_REQUEST", url);
                            MyApp.getContext().startActivity(intent);

                        } else {
                            //FAIL
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            Toast.makeText(MyApp.getContext(), MyApp.getContext().getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Load and set content for an ImageView
     * @param url
     * @param v
     */
    public static void downloadImage(String url, ImageView v) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, v);
    }

    /**
     * Load and return next page of results
     * @param url
     * @return
     */
    public static String nextPage(String url){
        // We already call the api, result should be received
        if(url == "none"){
            // Return the async result
            return jsonData;
        }
        // Else, we need to load future results
        else if(isNetworkAvailable()) {
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
                        jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) { }
                        else { }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        // Network down
        else {
            Toast.makeText(MyApp.getContext(), MyApp.getContext().getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }
        // Return json in a String
        return jsonData;
    }

    /**
     * Check if the network is available
     * @return
     */
    private static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) MyApp.getContext().getSystemService(MyApp.getContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;

    }
//    private void alertUserAboutError() {
//        AlertDialogFragment dialog = new AlertDialogFragment();
//        dialog.show(MyApp.getContext().getFragmentManager(), "error_dialog");
//    }

}