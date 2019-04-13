package c.c.beijiazai;

import android.app.*;
import android.content.*;
import android.widget.*;
import java.io.*;
import java.net.*;

public class todo extends Activity {
    Context context;
	public final String IP = "123.207.65.93";
    public final int PORT = 9760;
    public void init(Context context) {
        this.context = context;
        showToast();
    }

    public void showToast() {
        Toast.makeText(context, "start", Toast.LENGTH_LONG).show();
    }

    public void setAdapter(ListView[] views){
		
	}
	
	
	
	public void getJsonString() {

        new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Socket client = new Socket(IP, PORT);
						OutputStream out = client.getOutputStream();
						DataOutputStream dos = new DataOutputStream(out);
						dos.writeUTF("get");
						InputStream in = client.getInputStream();
						DataInputStream dis = new DataInputStream(in);
						final String result = dis.readUTF();
						runOnUiThread(new Runnable() {

								@Override
								public void run() {
									//	Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
									loadConfigData(result);
								}
							});
						client.close();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
					}
				}
			}).start();
    }
	private void loadConfigData(String s) {
        configJavaBean = gson.fromJson(s, ConfigJavaBean.class);
        listViewGroupReply.setAdapter(new GroupReplyListAdapter(this, configJavaBean.groupReply));
        listViewQQNotReply.setAdapter(new QQNotReplyAdapter(this, configJavaBean.QQNotReply));
        listViewWordNotReply.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(configJavaBean.wordNotReply)));
        listViewGroupRepeater.setAdapter(new GroupRepeaterListAdapter(this, configJavaBean.groupRepeater));
        listViewGroupDicReply.setAdapter(new GroupDicListAdapter(this, configJavaBean.groupDicReply));
        listViewPersonInfo.setAdapter(new PersonInfoAdapter(this, configJavaBean.personInfo));
    }
	

}  
