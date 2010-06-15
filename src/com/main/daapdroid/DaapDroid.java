package com.main.daapdroid;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.app.TabActivity;

public class DaapDroid extends TabActivity {
	
	


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; 			// Resusable TabSpec for each tab
		Intent intent; 					// Reusable Intent for each tab
		
		/* SERVERS */
		intent = new Intent().setClass(this, ServersActivity.class);
		spec = tabHost.newTabSpec("servers").setIndicator("Servers",
				res.getDrawable(R.drawable.ic_tab_artists)).setContent(intent);
		tabHost.addTab(spec);

		/* ARTISTS */
		intent = new Intent().setClass(this, ArtistsActivity.class);
		spec = tabHost.newTabSpec("artists").setIndicator("Artists",
				res.getDrawable(R.drawable.ic_tab_artists)).setContent(intent);
		tabHost.addTab(spec);

		/* ALBUMS */
		intent = new Intent().setClass(this, AlbumsActivity.class);
		spec = tabHost.newTabSpec("albums").setIndicator("Albums",
				res.getDrawable(R.drawable.ic_tab_artists)).setContent(intent);
		tabHost.addTab(spec);

		/* SONGS */
		intent = new Intent().setClass(this, SongsActivity.class);
		spec = tabHost.newTabSpec("songs").setIndicator("Songs",
				res.getDrawable(R.drawable.ic_tab_artists)).setContent(intent);
		tabHost.addTab(spec);

		
		/* PLAYLISTS */
		intent = new Intent().setClass(this, PlaylistsActivity.class);
		spec = tabHost.newTabSpec("playlists").setIndicator("Playlists",
				res.getDrawable(R.drawable.ic_tab_artists)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

		

	}

	
}







