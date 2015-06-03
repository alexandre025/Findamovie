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

import net.hetic.findamovie.DisplayResults;
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

    public static void requestMovies(String genres) {

        String apiUrl = "http://api.themoviedb.org/3/discover/movie";
        String apiKey = "c1ac741d5dd740f9861e794c5363b0c2";

        url = apiUrl+"?api_key="+apiKey+"&"+genres;

        System.out.println(url);

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
            System.out.println("UNAVAILABLE");
            Toast.makeText(MyApp.getContext(), MyApp.getContext().getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }

    }

    public static void downloadImage(String url, ImageView v) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, v);
    }

    public static String nextPage(String url){
        if(url=="none"){
            return jsonData;
        }
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
                        if (response.isSuccessful()) { }
                        else { }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            System.out.println("UNAVAILABLE");
            Toast.makeText(MyApp.getContext(), MyApp.getContext().getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }
        return jsonData;
    }

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