package fr.alexandre_ferraille.findamovie.network;

import android.support.v7.app.AlertDialog;

import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;

/**
 * Created by alexandre on 22/05/16.
 */
public class NetworkManager {

    public static void networkUnavailableAlertDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(MyApp.getContext(), R.style.AlertDialog)
                .setTitle("Network error")
                .setMessage("Please verify your Internet connection")
                .show();

    }
}
