package net.hetic.findamovie;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.content.IntentFilter;

import net.hetic.findamovie.fragments.AlertDialogFragment;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener
{

    private Button buttonFindAMovie;
    private Button buttonMyMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFindAMovie = (Button) findViewById(R.id.buttonFindAMovie);
        buttonMyMovies = (Button) findViewById(R.id.buttonMyMovies);

        Typeface font = Typeface.createFromAsset(getAssets(), "Lato-Regular.ttf");
        buttonFindAMovie.setTypeface(font);
        buttonMyMovies.setTypeface(font);
    }

    @Override
    protected void onStart(){

        super.onStart();
        buttonMyMovies.setOnClickListener(this);
        buttonFindAMovie.setOnClickListener(this);
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
            Intent FindMovie = new Intent(this, FindMovie.class);
            startActivity(FindMovie);
        }
        //Toast.makeText(getApplicationContext(),"Push", Toast.LENGTH_LONG).show();
        if(v == buttonMyMovies) {
            Intent MyMovies = new Intent(this, MyMovies.class);
            startActivity(MyMovies);
        }
        //Toast.makeText(getApplicationContext(),"Push 2",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }
}
