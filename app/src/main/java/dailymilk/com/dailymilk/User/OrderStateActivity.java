package dailymilk.com.dailymilk.User;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import dailymilk.com.dailymilk.R;

public class OrderStateActivity extends AppCompatActivity {

    public final static String EXTRA_RESULT = "result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_state);

        LinearLayout ll = (LinearLayout) findViewById(R.id.activity_order_state);
        ll.setOrientation(LinearLayout.VERTICAL);

        String result = getIntent().getStringExtra(EXTRA_RESULT);

        String[] resultArray = result.split(";");

        for(int i= 0, length = resultArray.length; i < length; i++) {
            //TODO: Erster Eintrag muss unter derzeitiger Bestellstatus, alle anderen unter History gesetzt werden.
            TextView tv = new TextView(this);
            tv.setText(resultArray[i]);
            if (i == 0) { tv.setTypeface(null, Typeface.BOLD); tv.setPadding(0,2, 0, 5); }
            ll.addView(tv);
            //TODO: Split resultArray in another Array to get every Element of resultArray in an seperated TextView
        }
        //TODO: Grafisch Aufpeppen
    }
}
