package dailymilk.com.dailymilk.User;

<<<<<<< HEAD
import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.IntegerRes;
=======
>>>>>>> origin/master
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
=======
>>>>>>> origin/master

import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.R;

public class MainActivity extends AppCompatActivity  {

    public final static String EXTRA_MESSAGE = "Username";
    public final static String EXTRA_DRINKS = "DRINK";
    String orderedItem;
    String username;
<<<<<<< HEAD
    String time;
    int pictures[] = {R.drawable.dose, R.drawable.flasche, R.drawable.tetrapack};
=======
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        String res = getIntent().getStringExtra(EXTRA_DRINKS);

        final String[] drinks = res.split(";");
        Spinner mySpinner = (Spinner) findViewById(R.id.simpleSpinner);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, resArray);*/

        CustomAdapter adapter=new CustomAdapter(getApplicationContext(), pictures, drinks);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setPadding(0,5,0,15);
        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderedItem = drinks[position].toString();
               // Toast.makeText(getApplicationContext(), orderedItem, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        }));

        username = getIntent().getStringExtra(EXTRA_MESSAGE);
        getSupportActionBar().setTitle(username);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_orders:
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute("view",username);
                break;
        }
        return true;
    }

    public void onOrder(View view) {

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("order",username, orderedItem);
    }


}
