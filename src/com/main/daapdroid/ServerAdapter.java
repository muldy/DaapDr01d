package com.main.daapdroid;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

class ServerAdapter extends ArrayAdapter<Server>  {
	
	
	private ArrayList<Server> items;
	private final Context context;

	public ServerAdapter(Context context, int textViewResourceId,ArrayList<Server> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.context = context;
	}
	@Override
	public Server getItem(int position)
	{
		return items.get(position);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.row, null);
		}
		Server o = items.get(position);
		if (o != null) {
			//LinearLayout ll = (LinearLayout) v.findViewById(R.id.row);
			
			//ll.setOnClickListener(this);
			
			TextView tt = (TextView) v.findViewById(R.id.toptext);
			TextView bt = (TextView) v.findViewById(R.id.bottomtext);
			if (tt != null) {
				tt.setText("Name: " + o.getServerName());
			}
			if (bt != null) {
				String info = o.getServerInfo().getServer() + o.getServerInfo().getPort();
				bt.setText("URI: " + info);
			}
		}
		return v;
	}


	


//	public void onClick(View v) {
//		// gotta get server 
//		LinearLayout ll = (LinearLayout) v.findViewById(R.id.row);
//		ll.removeAllViews();
//		
//	}
	
	
}