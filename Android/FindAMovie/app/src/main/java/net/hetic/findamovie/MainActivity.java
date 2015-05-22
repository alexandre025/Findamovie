package net.hetic.findamovie;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;


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
<<<<<<< HEAD
    }

    @Override
    protected void onStart(){

        super.onStart();

        buttonFindAMovie.setOnClickListener(this);

=======
        System.out.println("BREAAKPOIINT");
>>>>>>> 44d43e2c8055217bfa94fd31e6ad18f7118a15d4
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
<<<<<<< HEAD
    public void onClick(View v) {
        if(v == buttonFindAMovie) {
            System.out.println("BREAAKPOIINT");
=======
    public void onClick(View v)
    {
        if (v == buttonFindAMovie)
        {
>>>>>>> 44d43e2c8055217bfa94fd31e6ad18f7118a15d4
            Toast.makeText(getApplicationContext(), "Push", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }
}
