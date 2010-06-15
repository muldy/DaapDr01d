package com.main.daapdroid;

import java.io.IOException;
import java.util.ArrayList;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



public class ServersActivity extends Activity {
	
	private static final String TAG = "DaapDr01d";
	private static ArrayList<Server> mServers =null;
	private ServerAdapter m_adapter;
	private ProgressDialog m_ProgressDialog = null;
	private Runnable viewServers;
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {

	    case R.id.itemQuit:
	        super.finish();
	        return true;
	    case R.id.rescan:
	    	scanServers();
	    	return true;
	    }
	    return false;
	}
	
	
	
	class ServerAdapter extends ArrayAdapter<Server> implements android.view.View.OnClickListener {
		
		private ArrayList<Server> items;

		public ServerAdapter(Context context, int textViewResourceId,
				ArrayList<Server> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.row, null);
			}
			Server o = items.get(position);
			if (o != null) {
				LinearLayout ll = (LinearLayout) v.findViewById(R.id.row);
				
				ll.setOnClickListener(this);
				
				TextView tt = (TextView) v.findViewById(R.id.toptext);
				TextView bt = (TextView) v.findViewById(R.id.bottomtext);
				if (tt != null) {
					tt.setText("Name: " + o.getServerName());
				}
				if (bt != null) {
					bt.setText("URI: " + o.getServerType());
				}
			}
			return v;
		}


		public void onClick(View v) {
			// gotta get server 
			
			
		}
		
		
	}

	static class SampleListener implements ServiceListener {

		public void serviceAdded(ServiceEvent event) {
			Log.v(TAG, "Service added   : " + event.getName() + "." + event.getType());
			//mServers.add(new Server(event.getName(),event.getType()));
		}

		public void serviceRemoved(ServiceEvent event) {
			Log.v(TAG, "Service removed : " + event.getName() + "."
					+ event.getType());
			//ms.add(new Server(event.getName(),event.getType()));
		}

		public void serviceResolved(ServiceEvent event) {
			Log.v(TAG, "Service resolved: " + event.getInfo());
			
			//Server(String serverName,String serverType,ServiceInfo serverInfo,JmDNS serverDNS)
			mServers.add(new Server(event.getName(),
					event.getType(),
					event.getInfo(),event.getDNS()));
		}
	}

	private void getServers(){
        try{
        	
        	mServers = new ArrayList<Server>(); //don't recount
        	m_adapter.clear();
    		JmDNS jmdns;
    		getApplicationContext();
			WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    		MulticastLock wmmc = wm.createMulticastLock("mylock");
    		wmmc.acquire();
    		try {
    			jmdns = JmDNS.create();
    			jmdns.addServiceListener("_http._tcp.local.", new SampleListener());
    			Thread.sleep(25000);
    			jmdns.close();

    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
            
            //Log.i("ARRAY", ""+ m_orders.size());
          } catch (Exception e) { 
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          runOnUiThread(returnRes);
      }
	
	private Runnable returnRes = new Runnable() {

        public void run() {
        	
            if(mServers != null && mServers.size() > 0){
            int z  = mServers.size();
                m_adapter.notifyDataSetChanged();
                for(int i=0;i<z;i++)
                m_adapter.add(mServers.get(i));
            }
            m_ProgressDialog.dismiss();
            m_adapter.notifyDataSetChanged();
        }
    };
	
    public void scanServers()
    {
    	Thread thread =  new Thread(null, viewServers, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(ServersActivity.this,    
              "Please wait...", "Retrieving data ...", true);
    }

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.server);
		
		mServers = new ArrayList<Server>();
		this.m_adapter = new ServerAdapter(this, R.layout.row, mServers);
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(this.m_adapter);
        
        viewServers = new Runnable(){
            public void run() {
            	getServers();
            }
        };
        scanServers();
        

		
	}
}