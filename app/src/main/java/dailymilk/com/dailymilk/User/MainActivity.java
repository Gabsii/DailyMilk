package dailymilk.com.dailymilk.User;

import android.app.ActionBar;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.R;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "Username";
    public final static String EXTRA_DRINKS = "DRINK";
    String orderedItem;
    String username;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        String res = getIntent().getStringExtra(EXTRA_DRINKS);
        String[] resArray = res.split(";");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, resArray);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setPadding(0,5,0,15);
        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));

        username = getIntent().getStringExtra(EXTRA_MESSAGE);
        getSupportActionBar().setTitle(username);

        //TODO: vielleicht sowas wie onSwipe wo man dann nach rechts swiped statt auf den Order Button zu drücken (es wird nicht bestellt sondern angezeigt)
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
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

    public void goToOrders (View view) {
        //TODO: goToOrders Button in die Actionbar verschieben

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("view",username);
    }
}
