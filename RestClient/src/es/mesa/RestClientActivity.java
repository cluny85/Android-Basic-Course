package es.mesa;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RestClientActivity extends Activity {
	/** Called when the activity is first created. */
	
	public static final String URL2 = "http://10.0.2.2:8080/ServicioREST/rest/hello";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button botonBasica = (Button) findViewById(R.id.buttonBasica);
        botonBasica.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	peticionRestBasica(URL2);
              }
            });
	}
	
	/**
	 * Petición Basica a un sevicio REST
	 * @param url
	 */
	public void peticionRestBasica(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				showMsg("Peticion enviada");
				String xml = EntityUtils.toString(entity);
				EditText ed = (EditText) findViewById(R.id.ResponseText);
				ed.setText(xml);				
			}
		} catch (Exception e) {
			Log.e("peticionRestBasica",e.getMessage());
		}
	}
	/**
	 * Método para mostrar mensaje Toast en la pantalla del dispositivo
	 * @param message
	 */
	private void showMsg(String message) {
		Toast msg = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}
}