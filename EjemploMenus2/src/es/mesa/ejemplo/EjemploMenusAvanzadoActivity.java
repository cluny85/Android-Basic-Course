package es.mesa.ejemplo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EjemploMenusAvanzadoActivity extends Activity {
	/** Called when the activity is first created. */
	private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;
	private static final int MNU_OPC3 = 3;
	private static final int MNU_OPC4 = 4;
	private static final int SMNU_OPC1 = 31;
	private static final int SMNU_OPC2 = 32;
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

	private void construirMenu(Menu menu, boolean extendido) {
		menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion1").setIcon(
				R.drawable.tag);
		menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, "Opcion2").setIcon(
				R.drawable.filter);
		SubMenu smnu = menu.addSubMenu(Menu.NONE, MNU_OPC3, Menu.NONE,
				"Opcion3").setIcon(R.drawable.chart);
		smnu.add(GRUPO_MENU_1, SMNU_OPC1, Menu.NONE, "Opcion 3.1");
		smnu.add(GRUPO_MENU_1, SMNU_OPC2, Menu.NONE, "Opcion 3.2");
		// Establecemos la selección exclusiva para el grupo de opciones
		smnu.setGroupCheckable(GRUPO_MENU_1, true, true);
		// Marcamos la opción seleccionada actualmente
		if (extendido)
			menu.add(Menu.NONE, MNU_OPC4, Menu.NONE, "Opcion4").setIcon(
					R.drawable.tag);
		// Marcamos la opción seleccionada actualmente
		if (opcionSeleccionada == 1)
			smnu.getItem(0).setChecked(true);
		else if (opcionSeleccionada == 2)
			smnu.getItem(1).setChecked(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		construirMenu(menu,true);
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
			opcionSeleccionada = 1;
			item.setChecked(true);
			return true;
		case SMNU_OPC2:
			opcionSeleccionada = 2;
			item.setChecked(true);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
