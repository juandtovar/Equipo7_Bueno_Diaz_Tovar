package com.example.unet.logic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unet.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Conf extends AppCompatActivity{

    private EditText e_text1;
    private Spinner spinner_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf);

        //obtener
        e_text1= findViewById(R.id.editText_user);
        spinner_plan= findViewById(R.id.spinner_planes);

        String[] planes = {"Ingeniería Mecánica", "Ingeniería Mecatrónica", "Seleccione su plan"};
        final int listsize = planes.length - 1;
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, planes) {
            @Override
            public int getCount() {
                return(listsize);
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_plan.setAdapter(adapter);
        spinner_plan.setSelection(listsize);

        SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);

        String[] archivos = fileList();
        if(ArchivoExiste(archivos, "datos.txt")){
            try {
                spinner_plan.setSelection(Integer.parseInt(pref.getString("plan", "")));
                InputStreamReader archivo = new InputStreamReader(openFileInput("datos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String user_text = "";
                while(linea != null){
                    user_text = user_text + linea;
                    linea = br.readLine();
                    if(linea != null){
                        user_text += '\n';
                    }
                }
                br.close();
                archivo.close();
                e_text1.setText(user_text);
            }catch (IOException e){
                //
            }
        }
    }

    private boolean ArchivoExiste(String[] archivos, String NombreArchivo){
        for (String archivo : archivos)
            if (NombreArchivo.equals(archivo))
                return true;
        return false;
    }

    public void Ingresar(View view) {
        try {
            SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString("plan", String.valueOf(spinner_plan.getSelectedItemPosition()));
            edit.commit();
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("datos.txt", Activity.MODE_PRIVATE));
            archivo.write(e_text1.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException e){
            //
        }
        this.finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
