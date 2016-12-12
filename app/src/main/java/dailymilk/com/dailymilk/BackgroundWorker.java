package dailymilk.com.dailymilk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import dailymilk.com.dailymilk.User.MainActivity;

/**
 * Created by Admin on 28.11.2016.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    public BackgroundWorker(Context ctx){
        context = ctx;
    }
    String type;
    String user_name;

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url = "http://dailymilk.tk/login.php";
        String order_url = "http://dailymilk.tk/order.php";
        if(type.equals("login")) {
            try {
                user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream oS = httpURLConnection.getOutputStream();
                BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(oS, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bW.write(post_data);
                bW.flush();
                bW.close();
                oS.close();
                InputStream iS = httpURLConnection.getInputStream();
                BufferedReader bR = new BufferedReader(new InputStreamReader(iS, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bR.readLine()) != null) {
                    result += line;
                }
                bR.close();
                iS.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(type.equals("order")){
            String username = params[1];
            String order = params[2];
            String date = params[3];
            try {
                URL url = new URL(order_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + " = " + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("order", "UTF-8") + " = " + URLEncoder.encode(order, "UTF-8") + "&" +
                        URLEncoder.encode("date", "UTF-8") + " = " + URLEncoder.encode(date, "UTF-8");
                bW.write(data);
                bW.flush();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Order Success";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
       if(type.equals("login")) {
            if (result.equals("true")) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(MainActivity.EXTRA_MESSAGE, user_name);
                context.startActivity(intent);
            }
       }
        if (type.equals("order")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}



