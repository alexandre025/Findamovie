package net.hetic.findamovie.network;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
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
import net.hetic.findamovie.ui.activities.SelectGenres;
import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;

import java.io.IOException;

/**
 * Created by alexandre on 30/05/15.
 */
public class NetworkAccess {

    public static final String NEXT_PAGE = "nextPage";
    public static final String NEXT_PAGE_EXTRA = "nextPageExtra";

    public static final String TAG = NetworkAccess.class.getSimpleName();

    /**
     * Preload list of categories witch will be displayed in FindMovie activity
     */
    public static void requestGenres() {

        String url = UrlBuilder.baseGenres();

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
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {

                            // If response is successful we start a new Intent
                            Intent intent = new Intent(MyApp.getInstance().getApplicationContext(), SelectGenres.class);
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

        // Build the request with parameters
        final String url = UrlBuilder.baseDiscover()+"&"+genres;

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
                        String jsonData = response.body().string();
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
    public static void nextPage(String url){

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
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(NEXT_PAGE);
                            intent.putExtra(NEXT_PAGE_EXTRA,jsonData);
                            LocalBroadcastManager.getInstance(MyApp.getContext()).sendBroadcast(intent);
                        }
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
    }

    /**
     * Check if the network is available
     * @return
     */
    public static boolean isNetworkAvailable() {
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