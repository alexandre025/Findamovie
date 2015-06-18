package net.hetic.findamovie.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;
import net.hetic.findamovie.network.NetworkAccess;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener
{

    private Button buttonFindAMovie;
    private Button buttonMyMovies;
    private ImageButton buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.transition_fadein, R.anim.transition_fadeout);

        setContentView(R.layout.activity_main);

        buttonFindAMovie = (Button) findViewById(R.id.buttonFindAMovie);
        buttonMyMovies = (Button) findViewById(R.id.buttonMyMovies);
        buttonSettings = (ImageButton) findViewById(R.id.settingsButton);

    }

    @Override
    protected void onStart(){

        super.onStart();
        buttonMyMovies.setOnClickListener(this);
        buttonFindAMovie.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        if(v == buttonFindAMovie) {
            NetworkAccess.requestGenres();
        }
        if(v == buttonMyMovies) {
            Intent MyMovies = new Intent(this, MyMovies.class);
            startActivity(MyMovies);
        }
        if(v == buttonSettings) {
            Toast.makeText(MyApp.getContext(), MyApp.getContext().getString(R.string.availability), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }
}
