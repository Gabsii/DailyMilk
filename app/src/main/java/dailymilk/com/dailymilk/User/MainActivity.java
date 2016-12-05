package dailymilk.com.dailymilk.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dailymilk.com.dailymilk.LoginActivity;
import dailymilk.com.dailymilk.R;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView DisplayUser = (TextView) findViewById(R.id.Username);
        String username = getIntent().getStringExtra(EXTRA_MESSAGE);

        DisplayUser.setText(username);
    }

    public void onOrder(View view) {
        Intent intent = new Intent(this, OrderStateActivity.class);
        startActivity(intent);
    }
}
