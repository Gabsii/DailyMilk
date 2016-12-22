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
        //TODO: vielleicht sowas wie onSwipe wo man dann nach rechts swiped statt auf den Order Button zu drücken (es wird nicht bestellt sondern angezeigt)
    }

    public void onOrder(View view) {
        time = sdf.format(new Date());

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("order",username, orderedItem);
    }
}
