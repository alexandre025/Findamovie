package net.hetic.findamovie.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.hetic.findamovie.DisplayResults;
import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;
import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.model.RequestedMovies;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alexandre on 30/05/15.
 */
public class NetworkAcces {

    public static final String TAG = NetworkAcces.class.getSimpleName();

    public static void requestMovies(String genres) {

        String apiUrl = "http://api.themoviedb.org/3/discover/movie";
        String apiKey = "c1ac741d5dd740f9861e794c5363b0c2";

        String url = apiUrl+"?api_key="+apiKey+"&"+genres;

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
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {

                            RequestedMovies mRequestedMovies = getResult(jsonData);
                            ArrayList<Movie> test = mRequestedMovies.getResults();
                            test.remove(0);
                            System.out.println(test.get(0).getTitle());

                            Intent intent = new Intent(MyApp.getInstance().getApplicationContext(), DisplayResults.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("REQUESTED_MOVIES", jsonData);
                            MyApp.getContext().startActivity(intent);

                        } else {
                            //FAIL
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (JSONException e) {
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

    private static RequestedMovies getResult(String jsonData) throws JSONException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        RequestedMovies mResult = null;
        try {
            mResult = mapper.readValue(jsonData, RequestedMovies.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResult;

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