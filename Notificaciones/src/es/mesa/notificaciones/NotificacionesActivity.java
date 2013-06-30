package es.mesa.notificaciones;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificacionesActivity extends Activity {
    /** Called when the activity is first created. */
    
	private Button not;
	private static int NOTIF_ALERTA_ID=1; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        not = (Button) findViewById(R.id.not);
        
        not.setOnClickListener(new View.OnClickListener() {

    	    public void onClick(View view) {
    	    	enviarNotificacion();
    	    }

    	});
    }
	
	private void enviarNotificacion() {
		//Obtenemos una referencia al servicio de notificaciones
    	String ns = Context.NOTIFICATION_SERVICE;
    	NotificationManager notManager =(NotificationManager) getSystemService(ns);
    	
    	//Configuramos la notificación
    	int icono = android.R.drawable.stat_sys_warning;
    	CharSequence textoEstado = "Alerta!";
    	long hora = System.currentTimeMillis();
    	Notification notif = new Notification(icono, textoEstado, hora);

    	//Configuramos el Intent
    	Context contexto = getApplicationContext();
    	CharSequence titulo = "Mensaje de Alerta";
    	CharSequence descripcion = "Ejemplo de notificación.";
    	Intent notIntent = new Intent(contexto,NotificacionesActivity.class);
    	
    	//AutoCancel: cuando se pulsa la notificaión ésta desaparece
    	notif.flags |= Notification.FLAG_AUTO_CANCEL;
    	//Añadir sonido, vibración y luces
    	notif.defaults |= Notification.DEFAULT_SOUND;
    	notif.defaults |= Notification.DEFAULT_VIBRATE;
    	//notif.defaults |= Notification.DEFAULT_LIGHTS;

    	
    	//Configuramos la notificación para que al pulsar sobre ella
    	//desaparezca de la bandeja de entrada (Notification.FLAG_AUTO_CANCEL)
    	PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, Notification.FLAG_AUTO_CANCEL);
    	notif.setLatestEventInfo(contexto, titulo, descripcion, contIntent);

    	//Enviar notificación
    	notManager.notify(NOTIF_ALERTA_ID, notif);
		

	}
    
}