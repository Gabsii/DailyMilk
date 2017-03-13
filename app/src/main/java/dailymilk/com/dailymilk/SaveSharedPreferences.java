package dailymilk.com.dailymilk;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SaveSharedPreferences {
    static final String PREF_USER_NAME = "username";
    static final String PREF_PASSWORD = "password";
    static final int ADMIN_STATE = 0;

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void setPassword(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PASSWORD, userName);
        editor.commit();
    }

    public static String getPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_PASSWORD, "");
    }

    public static void setAdminState(Context ctx, int state) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(String.valueOf(ADMIN_STATE), state);
        editor.commit();
    }

    public static int getAdminState(Context ctx) {
        return getSharedPreferences(ctx).getInt(String.valueOf(ADMIN_STATE), 0);
    }
}
