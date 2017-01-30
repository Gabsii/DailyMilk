package dailymilk.com.dailymilk.Admin;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dailymilk.com.dailymilk.BackgroundWorker;
import dailymilk.com.dailymilk.R;

import static android.R.attr.layout_alignParentRight;
import static android.R.attr.width;

public class OpenOrdersActivity extends AppCompatActivity {


    public final static String EXTRA_RESULT = "";
    public final static String EXTRA_USERNAME = "";
    private final int sdk = Build.VERSION.SDK_INT;
    ArrayList<String> selectedItems = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        String user = getIntent().getStringExtra(EXTRA_USERNAME);
        LinearLayout rl = (LinearLayout) findViewById(R.id.activity_open_orders);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String result = getIntent().getStringExtra(EXTRA_RESULT);
        String[] resultArray = result.split(";");

        ListView chl = (ListView) findViewById(R.id.checkableList);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.txt_lan, resultArray);
        chl.setAdapter(adapter);
        chl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                } else {
                    selectedItems.add(selectedItem);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void showSelectedItems(View view){
        String items = "";
        for(String item:selectedItems){
            items+="-"+item+"\n";
        }
        Toast.makeText(this, "You selected \n"+items, Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.openorders, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_delete:

                //remove all selected Entries
                break;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

        /*for(int i= 0, length = resultArray.length; i < length; i++) {
            TextView tv = new TextView(this);
            CheckBox b = new CheckBox(this);

            //LinearLayout l = new LinearLayout(this);
            //l.setOrientation(LinearLayout.HORIZONTAL);
            /*
            <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:id="@+id/activity_open_orders"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            tools:context="dailymilk.com.dailymilk.Admin.OpenOrdersActivity">
            </LinearLayout>

            tv.setText(resultArray[i]);
            tv.setId(i);
            l.addView(tv);
            l.addView(b);
            rl.addView(l);
        }
    }*/