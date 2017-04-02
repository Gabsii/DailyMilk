package dailymilk.com.dailymilk.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.LoginActivity;
import dailymilk.com.dailymilk.R;
import dailymilk.com.dailymilk.SaveSharedPreferences;

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
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCredentialsBack();
                Toast.makeText(getApplicationContext(), SaveSharedPreferences.getUserName(getApplicationContext()) + SaveSharedPreferences.getPassword(getApplicationContext()), Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        String res = getIntent().getStringExtra(EXTRA_DRINKS);
        String[] resArray = res.split(";");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, resArray);

        Spinner mySpinner = (Spinner) findViewById(R.id.simpleSpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setPadding(0, 5, 0, 15);
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
        switch (item.getItemId()) {
            case R.id.action_orders:
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute("view", username);
                break;
            case R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void onOrder(View view) {

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("order", username, orderedItem);
    }

    @Override
    public void onBackPressed() {
        setCredentialsBack();
        this.finish();
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }

    private void setCredentialsBack(){
        SaveSharedPreferences.setPassword(this,"");
        SaveSharedPreferences.setUserName(this,"");
    }
}