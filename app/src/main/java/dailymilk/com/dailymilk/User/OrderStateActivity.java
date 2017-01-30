package dailymilk.com.dailymilk.User;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import dailymilk.com.dailymilk.R;

public class OrderStateActivity extends AppCompatActivity {

    public final static String EXTRA_RESULT = "result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_state);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LinearLayout ll = (LinearLayout) findViewById(R.id.activity_order_state);
        ll.setOrientation(LinearLayout.VERTICAL);

        String result = getIntent().getStringExtra(EXTRA_RESULT);

        String[] resultArray = result.split(";");

        for(int i= 0, length = resultArray.length; i < length; i++) {
            TextView tv = new TextView(this);
            tv.setText(resultArray[i]);
            if (i < 2) {
                if(i==0) {
                    TextView now = new TextView(this);
                    now.setText("pending Orders");
                    now.setTypeface(null, Typeface.BOLD);
                    now.setTextSize(20);
                    ll.addView(now);
                }else {
                    tv.setPadding(0, 2, 0, 15);
                }
            }
            else if(i == 2) {
                TextView history = new TextView(this);
                history.setText("History");
                history.setTypeface(null, Typeface.BOLD);
                history.setTextSize(20);
                ll.addView(history);
            }
            ll.addView(tv);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


