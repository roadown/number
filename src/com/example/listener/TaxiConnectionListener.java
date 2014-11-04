package com.example.listener;

import java.util.Timer;
import java.util.TimerTask;

import org.jivesoftware.smack.ConnectionListener;

import android.util.Log;

//import com.techrare.taxicall.MainActivity;

import com.example.connect.XmppConnection;

/**
 * 连接监听类
 * 
 * @author Administrator
 * 
 */
public class TaxiConnectionListener implements ConnectionListener {
	private Timer tExit;
	private String username;
	private String password;
	private int logintime = 2000;

	@Override
	public void connectionClosed() {
		Log.i("TaxiConnectionListener", "连接关闭");
		// 關閉連接
		XmppConnection.getInstance().closeConnection();
		// 重连服务器
		tExit = new Timer();
		tExit.schedule(new timetask(), logintime);
	}

	@Override
	public void connectionClosedOnError(Exception e) {
		Log.i("TaxiConnectionListener", "连接关闭异常");
		// 判斷為帳號已被登錄
		boolean error = e.getMessage().equals("stream:error (conflict)");
		if (!error) {
			// 關閉連接
			XmppConnection.getInstance().closeConnection();
			// 重连服务器
			tExit = new Timer();
			tExit.schedule(new timetask(), logintime);
		}
	}

	class timetask extends TimerTask {
		@Override
		public void run() {
			// 暂时注掉 by alan 2014年10月30日16:02:28
			// username = Utils.getInstance().getSharedPreferences("taxicall",
			// "account", MainActivity.context);
			// password = Utils.getInstance().getSharedPreferences("taxicall",
			// "password", MainActivity.context);
			if (username != null && password != null) {
				Log.i("TaxiConnectionListener", "尝试登陆");
				// 连接服务器
				if (XmppConnection.getInstance().login(username, password)) {
					Log.i("TaxiConnectionListener", "登录成功");
				} else {
					Log.i("TaxiConnectionListener", "重新登录");
					tExit.schedule(new timetask(), logintime);
				}
			}
		}
	}

	@Override
	public void reconnectingIn(int arg0) {
	}

	@Override
	public void reconnectionFailed(Exception arg0) {
	}

	@Override
	public void reconnectionSuccessful() {
	}

}
