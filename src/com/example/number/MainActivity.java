package com.example.number;

import android.os.AsyncTask;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

//import com.example.utils.XmppConnection;


import org.jivesoftware.smack.packet.Message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button mButton = (Button) findViewById(R.id.login);
		//AsyncTask
		mButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				GetCSDNLogoTask task = new GetCSDNLogoTask();
				task.execute();
			}
		});
		
		// Button register = (Button) findViewById(R.id.registerAccount);
		// register.setOnClickListener(new android.view.View.OnClickListener() {
		// public void onClick(View v) {
		// Intent intent = new Intent(MainActivity.this,
		// RegisterActivity.class);
		// MainActivity.this.startActivity(intent);
		// }
		// });
	}

	class GetCSDNLogoTask extends AsyncTask<String, Integer, Boolean> {
		@Override
		protected Boolean doInBackground(String... arg0) {
			ConnectionConfiguration config = new ConnectionConfiguration(
					"218.22.27.208", 5222);
			// config.setReconnectionAllowed(true);
			// config.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
			config.setSASLAuthenticationEnabled(false);
			// config.setTruststorePath("/system/etc/security/cacerts.bks");
			// config.setTruststorePassword("changeit");
			// config.setTruststoreType("bks");
			// config.setDebuggerEnabled(false);
		
			try {
				XMPPConnection connection = new XMPPConnection(config);
		
				connection.connect();
				connection.login("alan", "123456");
				//XMPPConnection cc = ConnUtil.getConnection();
				ChatManager cm = connection.getChatManager();
				Chat chat=cm.createChat("lyj"+"@"+connection.getServiceName(), null);   
				Message m=new Message();
				m.setBody("第一条消息!");
				chat.sendMessage(m);
				return true;
			} catch (XMPPException e) {
				e.printStackTrace();
				return false;
			}

			// Intent intent = new Intent(MainActivity.this,
			// ButtonsActivity.class);
			// MainActivity.this.startActivity(intent);
		}

		protected void onPostExecute(Boolean result) {
			if (result) {
				Toast.makeText(MainActivity.this, "发送成功",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(MainActivity.this, "发送失败",
						Toast.LENGTH_LONG).show();
			}
		}

		protected void onProgressUpdate(Integer... progress) {

		}

		protected void onPreExecute() {

		}

		protected void onCancelled() {

		}
	}

	
}
