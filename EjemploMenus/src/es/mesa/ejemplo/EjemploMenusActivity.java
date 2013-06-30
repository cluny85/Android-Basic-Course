package es.mesa.ejemplo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;

public class EjemploMenusActivity extends Activity {
    /** Called when the activity is first created. */
	private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;
	private static final int MNU_OPC3 = 3;
	private static final int SMNU_OPC1 = 4;
	private static final int SMNU_OPC2 = 5;
	
	private EditText lblMensaje;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*lblMensaje=new EditText(getApplicationContext());
		lblMensaje.setId(R.id.lblMensaje);*/
		setContentView(R.layout.main);
		lblMensaje=(EditText) findViewById(R.id.lblMensaje);

	}

	/*
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) { 
		// Alternativa1 
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu); 
		return true; 
	}
	 */
	
	
	//...
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Alternativa 2
		menu.add(Menu.NONE, MNU_OPC1, Menu.NONE,"Opcion1").setIcon(R.drawable.tag);
		menu.add(Menu.NONE, MNU_OPC2, Menu.NONE,"Opcion2").setIcon(R.drawable.filter);
		//menu.add(Menu.NONE, MNU_OPC3, Menu.NONE,"Opcion3").setIcon(R.drawable.chart);
		SubMenu smnu = menu.addSubMenu(Menu.NONE, MNU_OPC3, Menu.NONE, "Opcion3")
		.setIcon(R.drawable.chart);
		smnu.add(Menu.NONE, SMNU_OPC1, Menu.NONE, "Opcion 3.1");
		smnu.add(Menu.NONE, SMNU_OPC2, Menu.NONE, "Opcion 3.2");

		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//int id=item.getItemId();
		switch (item.getItemId()) {
		case MNU_OPC1:
			lblMensaje.setText("Opcion 1 pulsada!");
			
			return true;
		case MNU_OPC2:
			lblMensaje.setText("Opcion 2 pulsada!");
			
			return true;
		case MNU_OPC3:
			lblMensaje.setText("Opcion 3 pulsada!");
			
			return true;
		case SMNU_OPC1:
			lblMensaje.setText("Opcion Submenu 3.1 pulsada!");
			
			return true;
		case SMNU_OPC2:
			lblMensaje.setText("Opcion Submenu 3.2 pulsada!");
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void onDestroy(){
		super.onDestroy();
		System.exit(1);
	}
}