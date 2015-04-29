package com.android.francis.metabolismobasal;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity implements OnClickListener, SeekBar.OnSeekBarChangeListener {

    private TextView tvValor,tvAccion;
    private SeekBar sb;


    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;

    private List<String> lista;
    private List<String> lista2;
    private List<String> lista3;


    private TextView resultadoTextView;
    private Button mainButton;

    // Almacenamos el nombre entre ejecuciones
    private static final String PREFS = "prefs";
    private static final String PREF_NAME = "name";
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. Rellenar los datos por defecto de los spinners.
        datosPorDefecto();

        // 2. Access the Button defined in layout XML
        // and listen for it here
        mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);

        // 3. SeekBar Peso
        tvValor = (TextView) findViewById(R.id.tvSeekBarValor);
        tvAccion = (TextView) findViewById(R.id.tvSeekBarAccion);

        sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setMax(200);
        sb.setOnSeekBarChangeListener(this);

        // Greet the user, or ask for their name if new
        displayWelcome();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        tvValor.setText("Peso " + progress);
        tvAccion.setText("Cambiando Valor");

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        tvAccion.setText("Comenzando");

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        tvAccion.setText("Finalizado");

    }

    private void datosPorDefecto() {
        //Peso en kg
        /*spinner1 = (Spinner) findViewById(R.id.spinnerPeso);
        lista = new ArrayList<String>();
        spinner1 = (Spinner) this.findViewById(R.id.spinnerPeso);
        //Hacer un bucle para rellenar la lista. Mínimo 40 Kg. Máximo 140 kg.
        for (int i=40; i<140; i++) {
            lista.add(Integer.toString(i));
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador);*/

        //Altura en cm
        spinner2 = (Spinner) findViewById(R.id.spinnerAltura);
        lista2 = new ArrayList<String>();
        spinner2 = (Spinner) this.findViewById(R.id.spinnerAltura);
        //Hacer un bucle para rellenar la lista. Mínimo 120 cm. Máximo 220 cm.
        for (int i=120; i<220; i++) {
            lista2.add(Integer.toString(i));
        }
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista2);
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adaptador2);

        //Edad
        spinner3 = (Spinner) findViewById(R.id.spinnerEdad);
        lista3 = new ArrayList<String>();
        spinner3 = (Spinner) this.findViewById(R.id.spinnerEdad);
        //Hacer un bucle para rellenar la lista. Mínimo 15 años. Máximo 90 años.
        for (int i=15; i<90; i++) {
            lista3.add(Integer.toString(i));
        }
        ArrayAdapter<String> adaptador3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista3);
        adaptador3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adaptador3);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Al pulsar el botón Calcular: obtener los valores de Sexo, Peso, Altura y Edad y realizar el cálculo.
    // Escribir el valor en mainTextView
    @Override
    public void onClick(View v) {
        double calorias;
        RadioGroup rdgSexo;

        // Obtener peso
        //SeekBar sbPeso=(SeekBar) findViewById(R.id.seekBarPeso);
        int peso =  (Integer) sb.getProgress();

        // Obtener altura
        Spinner mySpinner=(Spinner) findViewById(R.id.spinnerAltura);
        int altura =  Integer.valueOf((String) mySpinner.getSelectedItem());

        // Obtener edad
        mySpinner=(Spinner) findViewById(R.id.spinnerEdad);
        int edad =  Integer.valueOf((String) mySpinner.getSelectedItem());

        // Cálculo de las calorias. La fórmula depende del Sexo
        // Hombres	TMB = (10 x peso en kg) + (6,25 × altura en cm) - (5 × edad en años) + 5
        // Mujeres	TMB = (10 x peso en kg) + (6,25 × altura en cm) - (5 × edad en años) - 161
        // Obtener sexo

        rdgSexo=(RadioGroup) findViewById(R.id.rdgSexo);

        /*Toast.makeText(MainActivity.this,
                "Calcular para "+ ((RadioButton) this.findViewById(rdgSexo.getCheckedRadioButtonId())).getText().toString(),
                Toast.LENGTH_SHORT).show();
        */

        // Valor del botón Sexo pulsado, Hombre o Mujer.
        String strBoton = ((RadioButton) this.findViewById(rdgSexo.getCheckedRadioButtonId())).getText().toString();

        // Para comparar, como son objetos, no podemos emplear ==, sino equals, para comparar sus valores.
        if (strBoton.equals(this.getString(R.string.rdbHombre))){
            calorias = (10*peso) + (6.25*altura) - (5*edad) +5;
        }
        else {
            calorias = (10*peso) + (6.25*altura) - (5*edad) -161;
        }

        resultadoTextView = (TextView) findViewById(R.id.resultado_textview);

        // Formatear las calorias para mostrar en pantalla
        NumberFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
        String strCalorias = df2.format(calorias);

        resultadoTextView.setText(strCalorias);
    }

    public void displayWelcome() {

        // Access the device's key-value storage
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        // Read the user's name,
        // or an empty string if nothing found
        String name = mSharedPreferences.getString(PREF_NAME, "");

        if (name.length() > 0) {

            // If the name is valid, display a Toast welcoming them
            Toast.makeText(this, "¡Bienvenido, " + name + "!", Toast.LENGTH_LONG).show();

        } else {

            // otherwise, show a dialog to ask for their name
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("¡Hola!");
            alert.setMessage("¿Cual es tu nombre?");

            // Create EditText for entry
            final EditText input = new EditText(this);
            alert.setView(input);

            // Make an "OK" button to save the name
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {

                    // Grab the EditText's input
                    String inputName = input.getText().toString();

                    // Put it into memory (don't forget to commit!)
                    SharedPreferences.Editor e = mSharedPreferences.edit();
                    e.putString(PREF_NAME, inputName);
                    e.commit();

                    // Welcome the new user
                    Toast.makeText(getApplicationContext(), "¡Bienvenido, " + inputName + "!", Toast.LENGTH_LONG).show();
                }
            });

            // Make a "Cancel" button
            // that simply dismisses the alert
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {}
            });

            alert.show();
        }

    }

}
