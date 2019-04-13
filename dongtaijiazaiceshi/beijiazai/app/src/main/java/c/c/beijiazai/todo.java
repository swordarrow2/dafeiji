package c.c.beijiazai;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class todo extends Activity {
    Context context;

    public void init(Context context, Button btn) {
        this.context = context;
        btn.setText("发发发");
        showToast();
    }

    public void showToast() {
        Toast.makeText(context, "start", Toast.LENGTH_LONG).show();
    }

    public int showToast2() {
        Toast.makeText(context, "openqq pause", Toast.LENGTH_LONG).show();
        return 100;
    }

    private void launchQQ() {
        //ComponentName componetName = new ComponentName("com.downloading.main.baiduyundownload","com.downloading.main.baiduyundownload.home.D");
        ComponentName componetName = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity");
        try {
            Intent intent = new Intent();
            intent.setComponent(componetName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }


}  
