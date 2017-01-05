package dailymilk.com.dailymilk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    ImageView logo;
    Button login, firstLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.UsernameInput);
        password = (EditText) findViewById(R.id.PasswordInput);
        logo = (ImageView) findViewById(R.id.Logo);
        login = (Button) findViewById(R.id.LoginButton);
        firstLogin = (Button) findViewById(R.id.FirstLoginButton);
    }
    //Ausblenden des Logos und des ersten Login-Buttons / Einblenden des "richtigen" Login-Buttons und der 2 EditTexts
    public void OnFirstLogin(View view) {
        firstLogin.setVisibility(View.INVISIBLE);
        logo.setVisibility(View.INVISIBLE);
        username.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        login.setVisibility(View.VISIBLE);
    }

    public void OnLogin(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, user, pass);
    }

}
