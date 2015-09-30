package br.com.refsoft.refsoft.activity.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Gabriel on 24/09/2015.
 */
public class MensagemUtil {

    public static void addMsg(Activity activity, String msg){
        Toast.makeText(activity, "Logou com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
