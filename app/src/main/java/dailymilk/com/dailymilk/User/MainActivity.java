package dailymilk.com.dailymilk.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.R;

import static dailymilk.com.dailymilk.User.OrderStateActivity.EXTRA_DATE;
import static dailymilk.com.dailymilk.User.OrderStateActivity.EXTRA_ORDER;
import static dailymilk.com.dailymilk.User.OrderStateActivity.EXTRA_USER;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "Username";
    SimpleDateFormat sdf;
    String orderedItem;
    String username;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        TextView DisplayUser = (TextView) findViewById(R.id.Username);

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
        sdf = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");


        DisplayUser.setText(username);

    }

    public void onOrder(View view) {
        time = sdf.format(new Date());

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("order",username, orderedItem);

        Intent intent = new Intent(this, OrderStateActivity.class);
        intent.putExtra(EXTRA_DATE, time);
        intent.putExtra(EXTRA_ORDER, orderedItem);
        intent.putExtra(EXTRA_USER, username);
        startActivity(intent);
    }
}
