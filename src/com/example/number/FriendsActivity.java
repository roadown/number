package com.example.number;

import java.util.ArrayList;
import java.util.HashMap;

import org.jivesoftware.smack.XMPPConnection;

import com.example.connect.XmppConnection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.view.View;

import com.example.number.R;

public class FriendsActivity extends Activity {
	private ListView listview = null;
	private Handler handler = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		
		listview = (ListView) findViewById(R.id.listView1);
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ItemTitle", "1111");
			map.put("ItemText", "22222");
			mylist.add(map);
		}

		SimpleAdapter sa = new SimpleAdapter(this, mylist, R.layout.friends_listitem, new String[] {"ItemTitle", "ItemText" }, new int[] {R.id.ItemTitle, R.id.ItemText });

		listview.setAdapter(sa);
		

		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (true) {//条件改成好友的数量
//					listview = (ListView) findViewById(R.id.listView1);
//					ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
//					for (int i = 0; i < 5; i++) {
//						HashMap<String, String> map = new HashMap<String, String>();
//						map.put("ItemTitle", "1111");
//						map.put("ItemText", "22222");
//						mylist.add(map);
//					}
//
//					SimpleAdapter sa = new SimpleAdapter(this, mylist, R.layout.friends_listitem, new String[] {"ItemTitle", "ItemText" }, new int[] {R.id.ItemTitle, R.id.ItemText });
//
//					listview.setAdapter(sa);
				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							"获取好友信息出错!", Toast.LENGTH_LONG);
					toast.show();
				}
			}
		};

		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				XmppConnection con = XmppConnection.getInstance();

				Message msg = Message.obtain();
				msg.obj = con.getAllEntries();
				handler.sendMessage(msg);
			}
		}).start();

		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent indent = new Intent(FriendsActivity.this,
						ButtonsActivity.class);
				FriendsActivity.this.startActivity(indent);
			}
		});
	}
}
