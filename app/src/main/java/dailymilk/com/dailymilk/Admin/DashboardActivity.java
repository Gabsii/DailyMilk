package dailymilk.com.dailymilk.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.LoginActivity;
import dailymilk.com.dailymilk.R;
import dailymilk.com.dailymilk.SaveSharedPreferences;

public class DashboardActivity extends AppCompatActivity {


    public final static String EXTRA_USER = "";
    final Context ctx = this;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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

        TextView tv = new TextView(this);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_dashboard);

        username = getIntent().getStringExtra(EXTRA_USER);
        getSupportActionBar().setTitle(username.toString());

        Button b = (Button) findViewById(R.id.openord);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(ctx);
                backgroundWorker.execute("admin", username);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
