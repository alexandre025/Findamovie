package net.hetic.findamovie.network;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.hetic.findamovie.MainActivity;
import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;
import net.hetic.findamovie.fragments.AlertDialogFragment;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alexandre on 30/05/15.
 */
public class NetworkAcces {

    public static final String TAG = NetworkAcces.class.getSimpleName();
    public static Context mContext = MyApp.getContext();

    public static void requestMovies() {

        String apiUrl = "http://api.themoviedb.org/3/discover/movie";
        String apiKey = "c1ac741d5dd740f9861e794c5363b0c2";

        String url = apiUrl+"?api_key="+apiKey;

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
                        Log.v(TAG, response.body().string());
                        if (response.isSuccessful()) {

                        } else {

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