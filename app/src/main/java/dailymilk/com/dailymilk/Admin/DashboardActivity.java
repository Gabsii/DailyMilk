package dailymilk.com.dailymilk.Admin;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.R;

public class DashboardActivity extends AppCompatActivity {


    public final static String EXTRA_USER = "";
    String username;
    final Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView tv = new TextView(this);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_dashboard);

        username = getIntent().getStringExtra(EXTRA_USER);
        getSupportActionBar().setTitle(username.toString());

        Button b = (Button) findViewById(R.id.openord);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(ctx);
                backgroundWorker.execute("admin", username);
            }
        });
    }

}
