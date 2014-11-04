package com.example.number;

import android.R.string;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.example.connect.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Message;

//程序登陆页面
public class MainActivity extends Activity {
	private Handler handler = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if ((Boolean) msg.obj) {
					Intent intent = new Intent(MainActivity.this,
							FriendsActivity.class);
					MainActivity.this.startActivity(intent);
				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							"密码错误，请重新输入!", Toast.LENGTH_LONG);
					toast.show();
				}
			}
		};

		// 登录
		Button mButton = (Button) findViewById(R.id.btn_login);
		mButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText mUsername = (EditText) findViewById(R.id.username);
				EditText mPassword = (EditText) findViewById(R.id.password);
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();

				// Message msg = Message.obtain();
				// msg.obj = username + '|' + password;
				// handler.sendMessage(msg);

				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						XmppConnection con = new XmppConnection();
						con.openConnection();
						Boolean result = con.login("alan", "123456");

						Message msg = Message.obtain();
						if (result)
							msg.obj = true;
						else
							msg.obj = false;
						handler.sendMessage(msg);
					}
				}).start();
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
}
