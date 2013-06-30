package es.mesa.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DialogActivity extends Activity {
	/** Called when the activity is first created. */

	private static final int DIALOGO_TIPO_1 = 1;
	private static final int DIALOGO_TIPO_2 = 2;
	private static final int DIALOGO_TIPO_3 = 3;
	private static final int DIALOGO_TIPO_4 = 4;
	private static final int DIALOGO_TIPO_5 = 5;

	private Button dialogAl;
	private Button dialogConf;
	private Button dialogSel;
	private Button dialogSelSingle;
	private Button dialogSelMulti;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		dialogAl = (Button) findViewById(R.id.dialogAl);
		dialogAl.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOGO_TIPO_1);
			}

		});
		dialogConf = (Button) findViewById(R.id.dialogConf);
		dialogConf.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOGO_TIPO_2);
			}

		});
		dialogSel = (Button) findViewById(R.id.dialogSel);
		dialogSel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOGO_TIPO_3);
			}

		});
		dialogSelSingle = (Button) findViewById(R.id.dialogSingle);
		dialogSelSingle.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOGO_TIPO_4);
			}

		});
		dialogSelMulti = (Button) findViewById(R.id.dialogMulti);
		dialogSelMulti.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOGO_TIPO_5);
			}

		});
	}

	protected Dialog onCreateDialog(int id) {
		Dialog dialogo = null;
		switch (id) {
		case DIALOGO_TIPO_1:
			dialogo = crearDialogoAlerta();
			break;

		case DIALOGO_TIPO_2:
			dialogo = crearDialogoConfirmacion();
			break;
		case DIALOGO_TIPO_3:
			dialogo = crearDialogoSeleccion();
			break;
			
		case DIALOGO_TIPO_4:
			dialogo = crearDialogoSeleccionSingle();
			break;
			
		case DIALOGO_TIPO_5:
			dialogo = crearDialogoSeleccionMulti();
			break;

		default:
			dialogo = null;
			break;
		}
		return dialogo;
	}

	private Dialog crearDialogoAlerta() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Informacion");
		builder.setMessage("Esto es un mensaje de alerta.");
		builder.setPositiveButton("OK", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		return builder.create();
	}

	private Dialog crearDialogoConfirmacion() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Confirmacion");
		builder.setMessage("¿Confirma la accion seleccionada?");
		builder.setPositiveButton("Aceptar", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Dialogos", "Confirmacion Aceptada.");
				dialog.cancel();
			}
		});
		builder.setNegativeButton("Cancelar", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Dialogos", "Confirmacion Cancelada.");
				dialog.cancel();
			}
		});
		return builder.create();
	}

	private Dialog crearDialogoSeleccion() {
		final String[] items = { "Español", "Inglés", "Francés" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selección");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Log.i("Dialogos", "Opción elegida: " + items[item]);
			}
		});
		builder.setPositiveButton("Aceptar", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Dialogos", "Confirmacion Aceptada.");
				dialog.cancel();
			}
		});
		builder.setNegativeButton("Cancelar", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Dialogos", "Confirmacion Cancelada.");
				dialog.cancel();
			}
		});
		return builder.create();
	}

	private Dialog crearDialogoSeleccionSingle() {
		final String[] items = { "Español", "Inglés", "Francés" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selección");
		
		//-1 para que no se seleccione ninguno al inicio
		builder.setSingleChoiceItems(items, -1,new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Log.i("Dialogos", "Opción elegida: " + items[item]);
			}
		});
		builder.setPositiveButton("Aceptar", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Dialogos", "Confirmacion Aceptada.");
				dialog.cancel();
			}
		});
		
		return builder.create();
	}
	
	private Dialog crearDialogoSeleccionMulti() {
		final String[] items = { "Español", "Inglés", "Francés" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selección");

		builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
			public void onClick(DialogInterface dialog, int item, boolean isChecked) {
				Log.i("Dialogos", "Opción elegida: " + items[item]);
			}
		});
		
		builder.setPositiveButton("Aceptar", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Dialogos", "Confirmacion Aceptada.");
				dialog.cancel();
			}
		});
		
		return builder.create();
	}

}