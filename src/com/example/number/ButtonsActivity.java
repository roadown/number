package com.example.number;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.packet.Message;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.connect.*;

public class ButtonsActivity extends Activity {
	private static Vibrator vibrator;

	android.view.View.OnClickListener mViewClickListener = new android.view.View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			long[] pattern = { 100, 400 };
			boolean vibrator_open = true;

			int id = v.getId();
			switch (id) {
			case R.id.btn1:
				// XMPPConnection connection = XmppConnection.getInstance()
				// .getConnection();
				// ChatManager cm = connection.getChatManager();
				// Chat chat = cm.createChat("lyj" + "@"
				// + getConnection().getServiceName(), null);
				// Message m = new Message();
				// m.setBody("1");
				// chat.sendMessage(m);
				if (vibrator_open)
					vibrator.vibrate(pattern, 1);
				break;
			case R.id.btn2:
				// m.setBody("2");
				// chat.sendMessage(m);
				if (vibrator_open)
					vibrator.vibrate(pattern, 2);
				break;
			case R.id.btn3:
				// m.setBody("3");
				// chat.sendMessage(m);
				if (vibrator_open)
					vibrator.vibrate(pattern, 3);
				break;
			case R.id.btn4:
				// m.setBody("4");
				// chat.sendMessage(m);
				if (vibrator_open)
					vibrator.vibrate(pattern, 4);
				break;
			case R.id.btn5:
				// m.setBody("5");
				// chat.sendMessage(m);
				if (vibrator_open)
					vibrator.vibrate(pattern, 5);
				break;
			case R.id.btn6:
				// m.setBody("6");
				// chat.sendMessage(m);
				if (vibrator_open)
					vibrator.vibrate(pattern, 6);
				break;
			}
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buttons);

		Button btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(mViewClickListener);
		Button btn2 = (Button) findViewById(R.id.btn2);
		btn2.setOnClickListener(mViewClickListener);
		Button btn3 = (Button) findViewById(R.id.btn3);
		btn3.setOnClickListener(mViewClickListener);
		Button btn4 = (Button) findViewById(R.id.btn4);
		btn4.setOnClickListener(mViewClickListener);
		Button btn5 = (Button) findViewById(R.id.btn5);
		btn5.setOnClickListener(mViewClickListener);
		Button btn6 = (Button) findViewById(R.id.btn6);
		btn6.setOnClickListener(mViewClickListener);
	}

}
