package net.hetic.findamovie.network;

import java.util.ArrayList;

/**
 * Created by alexandre on 30/05/15.
 */
public class NetworkAcces {

    public static void requestMovies(ArrayList<String> params) {

        String url = "http://api.themoviedb.org/3/discover/movie?api_key=c1ac741d5dd740f9861e794c5363b0c2&";

        // ICI GENERER LA QUERY AVEC params

//        JacksonRequest<RequestedMovies> request = new
//                JacksonRequest<RequestedMovies>(Request.Method.GET, url, RequestedMovies.class,
//                new Response.Listener<RequestedMovies>() {
//                    @Override
//                    public void onResponse(RequestedMovies mRequestedMovies) {
//                        Intent intent = new Intent("DisplayResults");
//                        intent.putExtra("mRequestedMovies", (Serializable) mRequestedMovies);
//
//                        LocalBroadcastManager.getInstance(MyApp.getInstance().getApplicationContext().sendBroadcast(intent));
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//
//                    }
//                });
//
//                request.setTag("getUserSessionTag");
//
//                MyApp.getInstance().getRequestQueue().add(request);
    }
}