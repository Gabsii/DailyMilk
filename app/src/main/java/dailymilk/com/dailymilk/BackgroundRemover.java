package dailymilk.com.dailymilk;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
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
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by Gabsii on 28.11.2016.
 */

public class BackgroundRemover extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    String list;

    public BackgroundRemover(Context ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Checking Status");
    }

    @Override
    protected String doInBackground(String... params) {
        list = params[0];
        String check_url = "http://dailymilk.tk/check.php";
        String result;
        try {
            URL url = new URL(check_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream oS = httpURLConnection.getOutputStream();
            BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(oS, "UTF-8"));
            String post_data = URLEncoder.encode("list", "UTF-8") + "=" + URLEncoder.encode(list.toString(), "UTF-8");
            bW.write(post_data);
            bW.flush();
            bW.close();
            oS.close();
            InputStream iS = httpURLConnection.getInputStream();
            BufferedReader bR = new BufferedReader(new InputStreamReader(iS, "iso-8859-1"));
            result = "";
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
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(context, "sucessfully removed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}




