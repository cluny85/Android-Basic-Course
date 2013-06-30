package es.mesa.ejemplo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EjemploMenusActivity extends Activity {
	/** Called when the activity is first created. */
	private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;
	private static final int MNU_OPC3 = 3;
	private static final int SMNU_OPC1 = 4;
	private static final int SMNU_OPC2 = 5;
	private static final int GRUPO_MENU_1 = 101;
	private int opcionSeleccionada = 0;

	private TextView lblMensaje;
	private ListView lstLista;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Obtenemos las referencias a los controles
		lblMensaje = (TextView) findViewById(R.id.lblMensaje);
		lstLista = (ListView) findViewById(R.id.lstLista);
		// Rellenamos la lista con datos de ejemplo
		String[] datos = new String[] { "Elem1", "Elem2", "Elem3", "Elem4",
				"Elem5" };
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, datos);
		lstLista.setAdapter(adaptador);
		// Asociamos los menús contextuales a los controles
		registerForContextMenu(lblMensaje);
		registerForContextMenu(lstLista);

	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Alternativa1
	 * MenuInflater inflater = getMenuInflater(); inflater.inflate(R.menu.menu,
	 * menu); return true; }
	 */

	// ...
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		if (v.getId() == R.id.lblMensaje)
			inflater.inflate(R.menu.menu_ctx_etiqueta, menu);
		else if (v.getId() == R.id.lstLista) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			menu.setHeaderTitle(lstLista.getAdapter().getItem(info.position)
					.toString());
			inflater.inflate(R.menu.menu_ctx_lista, menu);
		}

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.CtxLblOpc1:
			lblMensaje.setText("Etiqueta: Opcion 1 pulsada!");
			return true;
		case R.id.CtxLblOpc2:
			lblMensaje.setText("Etiqueta: Opcion 2 pulsada!");
			return true;
		case R.id.CtxLstOpc1:
			lblMensaje.setText("Lista[" + info.position
					+ "]: Opcion 1 pulsada!");
			return true;
		case R.id.CtxLstOpc2:
			lblMensaje.setText("Lista[" + info.position
					+ "]: Opcion 2 pulsada!");
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Alternativa 2
		menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion1").setIcon(
				R.drawable.tag);
		menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, "Opcion2").setIcon(
				R.drawable.filter);
		// menu.add(Menu.NONE, MNU_OPC3,
		// Menu.NONE,"Opcion3").setIcon(R.drawable.chart);
		SubMenu smnu = menu.addSubMenu(Menu.NONE, MNU_OPC3, Menu.NONE,
				"Opcion3").setIcon(R.drawable.chart);
		smnu.add(Menu.NONE, SMNU_OPC1, Menu.NONE, "Opcion 3.1");
		smnu.add(Menu.NONE, SMNU_OPC2, Menu.NONE, "Opcion 3.2");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
	

	public void onDestroy() {
		super.onDestroy();
		System.exit(1);
	}
}