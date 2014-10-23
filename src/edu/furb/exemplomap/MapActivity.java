package edu.furb.exemplomap;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MapActivity extends Activity {
	
	WebView webView1;
	LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		webView1 = (WebView) findViewById(R.id.webView1);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		LocationListener listener = new MeuListener();
		long minTime = 0;
		float minDistance = 0;
		
//		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, listener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class MeuListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			String latitudeStr = String.valueOf(location.getLatitude());
			String longitudeStr = String.valueOf(location.getLongitude());
			String url = "http://maps.googleapis.com/maps/api/staticmap?size=400x400&sensor=true&markers=color:red|%s,%s&zoom=15";
			
			webView1.loadUrl(String.format(url, latitudeStr, longitudeStr));
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		
	}
}
