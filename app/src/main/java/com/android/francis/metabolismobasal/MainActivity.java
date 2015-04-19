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
    private List<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datosPorDefecto();
    }

    private void datosPorDefecto() {
        spinner1 = (Spinner) findViewById(R.id.spinnerPeso);
        lista = new ArrayList<String>();
        spinner1 = (Spinner) this.findViewById(R.id.spinnerPeso);
        //Hacer un bucle
        lista.add("60");
        lista.add("61");
        lista.add("62");
        lista.add("63");
        lista.add("64");
        lista.add("65");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
