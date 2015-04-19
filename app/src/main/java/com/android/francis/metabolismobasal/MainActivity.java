package com.android.francis.metabolismobasal;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private List<String> lista;
    private List<String> lista2;
    private List<String> lista3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datosPorDefecto();
    }

    private void datosPorDefecto() {
        //Peso en kg
        spinner1 = (Spinner) findViewById(R.id.spinnerPeso);
        lista = new ArrayList<String>();
        spinner1 = (Spinner) this.findViewById(R.id.spinnerPeso);
        //Hacer un bucle para rellenar la lista. Mínimo 40 Kg. Máximo 140 kg.
        for (int i=40; i<140; i++) {
            lista.add(Integer.toString(i));
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador);

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

}
