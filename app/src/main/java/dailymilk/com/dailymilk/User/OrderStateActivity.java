package dailymilk.com.dailymilk.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dailymilk.com.dailymilk.R;

public class OrderStateActivity extends AppCompatActivity {

    public final static String EXTRA_DATE = "Date";
    public final static String EXTRA_USER = "Username";
    public final static String EXTRA_ORDER = "Order";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_state);

        TextView tv = (TextView) findViewById(R.id.tv);
        String date = getIntent().getStringExtra(EXTRA_DATE);
        String user = getIntent().getStringExtra(EXTRA_USER);
        String order = getIntent().getStringExtra(EXTRA_ORDER);

        String res = user + " ordered " + order + " at " + date;

        tv.setText(res);
    }
}
