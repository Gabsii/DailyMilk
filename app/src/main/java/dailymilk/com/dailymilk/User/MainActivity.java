package dailymilk.com.dailymilk.User;

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

import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.InstructionsActivity;
import dailymilk.com.dailymilk.R;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "Username";
    public final static String EXTRA_DRINKS = "DRINK";
    String orderedItem;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_exit_to_app_black_24dp);
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
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void onOrder(View view) {

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("order",username, orderedItem);
    }
}