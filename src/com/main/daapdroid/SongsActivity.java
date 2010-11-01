package com.main.daapdroid;


import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import org.git.client.daap.DaapHost;

import ca.odell.glazedlists.EventList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SongsActivity extends Activity {
	private String serverAdrs ="192.168.1.78";
	private int serverPort = 3689;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textview = new TextView(this);
		textview.setText("This is the Songs tab");
		setContentView(textview);
		
		Bundle b = this.getIntent().getExtras();
		
		if (b!=null)
		{
			serverAdrs = b.getString("serverAdrs");
			serverAdrs = serverAdrs.substring(0,serverAdrs.length()-1);
			serverPort = b.getInt("serverPort");
		}
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName(serverAdrs);
			JmDNS jm = javax.jmdns.JmDNS.create(ia);
			//new ServiceInfo("_http._tcp.local.", "foo._http._tcp.local.", 1234, 0, 0, "path=index.html")
			ServiceInfo si = jm.getServiceInfo("_http._tcp.local.dell.local.3689.","qq");
			
			//DaapHost dh = new DaapHost("test","",ia,serverPort);
			DaapHost dh = new DaapHost(si);
			dh.connect();
			EventList ev = dh.getSongs();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}
}