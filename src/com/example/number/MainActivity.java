package com.example.number;

import android.os.AsyncTask;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import com.example.connect.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//程序入口登陆页面
public class MainActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 登陆
		Button mButton = (Button) findViewById(R.id.btn_login);
		mButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 // AsyncTask,如果登陆成功，就跳转到好友列表窗口
				 GetDataTask task = new GetDataTask();
				 task.execute();
			}
		});

		// 注册
		Button register = (Button) findViewById(R.id.btn_register);
		register.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						RegisterActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});

		// 设置
		Button set = (Button) findViewById(R.id.btn_settings);
		set.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SettingsActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
	}

	class GetDataTask extends AsyncTask<String, Integer, Boolean> {
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(String... arg0) {
			ConnectionConfiguration config = new ConnectionConfiguration(
					"218.22.27.208", 5222, "");
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

				// ChatManager cm = connection.getChatManager();
				// Chat chat = cm.createChat(
				// "lyj" + "@" + connection.getServiceName(), null);
				// Message m = new Message();
				// m.setBody("hello!");
				// chat.sendMessage(m);
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
				Intent intent = new Intent(MainActivity.this,
						FriendsActivity.class);
				MainActivity.this.startActivity(intent);
			} else {
				Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_LONG)
						.show();
			}
		}

		protected void onProgressUpdate(Integer... progress) {
			super.onProgressUpdate(progress);
		}

		protected void onCancelled() {
			super.onCancelled();
		}
	}
}
