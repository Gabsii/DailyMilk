package dailymilk.com.dailymilk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    ImageView logo;
    Button login, firstLogin;
    public static int ADMIN_STATUS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkSession();

        init();
    }

    public void checkSession() {
        String user = SaveSharedPreferences.getUserName(this);
        String pass = SaveSharedPreferences.getPassword(this);

        if (SaveSharedPreferences.getUserName(LoginActivity.this).length() == 0) {
            // nothing happens, since the user has to log in first
        } else {
            // call Main Activity
            if (ADMIN_STATUS == 0) {
                //user mode
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute("login", user, pass);
                //TODO: Start login Animation
            } else {
                //admin mode
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute("login", user, pass);
                //TODO: Start login Animation
            }

        }
    }

    public void init() {
        username = (EditText) findViewById(R.id.UsernameInput);
        password = (EditText) findViewById(R.id.PasswordInput);
        login = (Button) findViewById(R.id.LoginButton);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    public void OnLogin(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        SaveSharedPreferences.setUserName(getApplicationContext(), user);
        SaveSharedPreferences.setPassword(getApplicationContext(), pass);

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("login", user, pass);
        username.setText("");
        password.setText("");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_instructions:
                Intent intent = new Intent(this, InstructionsActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkSession();
    }

    /*public void OnFirstLogin(View view) {
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        Animation myFadeInAnimation1 = AnimationUtils.loadAnimation(this, R.anim.fadein);

        logo.startAnimation(myFadeInAnimation);
        username.startAnimation(myFadeInAnimation1);
        password.startAnimation(myFadeInAnimation1);
        login.startAnimation(myFadeInAnimation1);

        firstLogin.setVisibility(View.INVISIBLE);
        logo.setVisibility(View.INVISIBLE);
        username.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        login.setVisibility(View.VISIBLE);
    }*/
}