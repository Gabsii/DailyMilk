package dailymilk.com.dailymilk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.UsernameInput);
        password = (EditText) findViewById(R.id.PasswordInput);

        //TODO: Am Anfang sieht man das Logo und einen Login Button, beim klicken auf den Login Button werden die 2 EditTexts eingeblendet
    }

    public void OnLogin(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, user, pass);
    }

}
