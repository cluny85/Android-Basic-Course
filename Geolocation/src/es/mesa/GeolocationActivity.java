package es.mesa;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class GeolocationActivity extends Activity {
	/** Called when the activity is first created. */
	private static LocationManager lm;
    private static LocationListener locationListener;
    private static String latitud;
    private static String longitud;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                locationListener);

		// Retrieve a list of location providers that have fine accuracy, no
		// monetary cost, etc
//		Criteria criteria = new Criteria();
//		criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		criteria.setCostAllowed(false);
//		
//		String providerName = locationManager.getBestProvider(criteria, true);
	}
	
	public class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location location) {
            Double lat = redondear(location.getLatitude(),5);
            latitud = lat.toString();
            Double longi = redondear(location.getLongitude(),5);
            longitud = longi.toString();
            Log.d("Geolocation","latitud: "+latitud+" longitud: "+longitud);
            Toast.makeText(GeolocationActivity.this, "latitud: "+latitud+" longitud: "+longitud, 1000).show();            
        }

        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            
        }

        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
            
        }
        
        public double redondear(double numero, int decimales ) {
            return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
        }



    }
	protected void onStop() {    
		super.onStop();    
		lm.removeUpdates(locationListener);
	}
}