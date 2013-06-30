package es.mesa.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import es.mesa.notificaciones.R;

public class ToastActivity extends Activity {
    /** Called when the activity is first created. */
    
	private Button notDef;
	private Button notGrav;
	private Button notPers; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        notDef = (Button) findViewById(R.id.notDef);
        notGrav = (Button) findViewById(R.id.notGrav);
        notPers = (Button) findViewById(R.id.notPers);
        	
        notDef.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	enviarNotificacionDef();
    	    }

    	});
        
        notGrav.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	enviarNotificacionGrav();
    	    }

    	});
        
        notPers.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	enviarNotificacionPers();
    	    }

    	});
    }

	protected void enviarNotificacionDef() {
		Toast toast1 = Toast.makeText(getApplicationContext(),
			"Toast por defecto", Toast.LENGTH_SHORT);
		toast1.show();
	}

	protected void enviarNotificacionGrav() {
		Toast toast2 =Toast.makeText(getApplicationContext(),
			"Toast con gravity", Toast.LENGTH_SHORT);
		toast2.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
		toast2.show();
	}
	
	protected void enviarNotificacionPers() {
		Toast toast3 = new Toast(getApplicationContext());
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout,
				(ViewGroup) findViewById(R.id.lytLayout));
		TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
		txtMsg.setText("Toast Personalizado");
		toast3.setDuration(Toast.LENGTH_SHORT);
		toast3.setView(layout);
		toast3.show();
	}
    
}