package es.mesa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PantallasPreferenciasActivity extends Activity {
	/** Called when the activity is first created. */
	private final String TAG = "PantallasPreferencias";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn = (Button) findViewById(R.id.btnPreferencias);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(PantallasPreferenciasActivity.this,
						PantallaOpciones.class));
			}
		});

		Button btnObtenerPreferencias = (Button) findViewById(R.id.btnObtenerOpciones);
		btnObtenerPreferencias.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SharedPreferences pref = PreferenceManager
						.getDefaultSharedPreferences(PantallasPreferenciasActivity.this);
				Log.i(TAG, "Opción 1: " + pref.getBoolean("opcion1", false));
				Log.i(TAG, "Opción 2: " + pref.getString("opcion2", ""));
				Log.i(TAG, "Opción 3: " + pref.getString("opcion3", ""));
			}
		});
	}
}