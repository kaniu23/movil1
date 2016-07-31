package com.facci.conversorvs1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityVS1 extends AppCompatActivity {
    final String [] dato=new String[] {"DOLAR","EURO","´PESO MEXICANO"};
    private Spinner monedaActualSP;
    private Spinner monedaCambioSP;
    private EditText valorCambioET;
    private TextView resultadoTV;

    final private double factorDolarEuro= 0.87;
    final private double factorPesoDolar= 0.54;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_vs1);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,dato);
        monedaActualSP =(Spinner) findViewById(R.id.modenActualSP);
        monedaActualSP.setAdapter(adaptador);


    }
    public void clickConvertir(View v){
        monedaActualSP =(Spinner) findViewById(R.id.modenActualSP);
        monedaCambioSP = (Spinner) findViewById(R.id.modenaCambioSP);
        valorCambioET=(EditText) findViewById(R.id.valorCambioET);
        resultadoTV=(TextView) findViewById(R.id.resultadoTV);

        String monedaActual= monedaActualSP.getSelectedItem().toString();
        String monedaCambio = monedaCambioSP.getSelectedItem().toString();

        double valorCambio = Double.parseDouble(valorCambioET.getText().toString());
        double resultado = procesarConversion(monedaActual,monedaCambio,valorCambio);

        if(resultado>0){
            resultadoTV.setText(String.format("Por %5.2f %s, ud recibira %5.2f %s",valorCambio,monedaActual,resultado,monedaCambio));
            valorCambioET.setText("");
        }else
        {
            resultadoTV.setText(String.format("ud recibira"));
            Toast.makeText(MainActivityVS1.this,"Las opciones elegidas no tienen un factor de conversion",Toast.LENGTH_LONG).show();

        }


    }
    private double procesarConversion(String monedaActual,String monedaCambio,double valorCambio){
        double resultadoConversion =0;
        switch(monedaActual){
            case "DOLAR":
                if(monedaCambio.equals("EURO"))
                    resultadoConversion=valorCambio * factorDolarEuro;

                if(monedaCambio.equals("PESO MEXICANO"))
                    resultadoConversion=valorCambio / factorPesoDolar;

                break;
            case "EURO":
                if(monedaCambio.equals("DOLAR"))
                    resultadoConversion= valorCambio / factorDolarEuro;

                break;
            case "PESO MEXICANO":
                if (monedaCambio.equals("DOLAR"));
                resultadoConversion= valorCambio * factorPesoDolar;

               break;


        }
        return resultadoConversion;
    }

}
