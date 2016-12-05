package dailymilk.com.dailymilk.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dailymilk.com.dailymilk.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onOrder(View view) {
        Intent intent = new Intent(this, OrderStateActivity.class);
        startActivity(intent);
    }
}
