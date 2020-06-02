package com.example.unet.logic;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.unet.R;
import com.example.unet.data.Chain;
import com.example.unet.data.Plan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String texto = "WTF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_MiAvance, R.id.nav_MiPlan).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Chain<Plan> planes = new Chain<>();/*
        try {
            Resources res = getResources();
            InputStream planesFile = res.openRawResource(R.raw.informacion_planes);
            try (Scanner readPlanes = new Scanner(planesFile)) {
                readPlanes.useDelimiter("/ ");
                readPlanes.nextLine();
                while (readPlanes.hasNext()) {
                    Plan plan = new Plan(readPlanes.next(), readPlanes.nextInt(),
                            readPlanes.nextInt(), readPlanes.nextInt(), readPlanes.nextInt());
                    planes.add(plan, planes.getSize());
                    InputStream file = res.openRawResource(R.raw.plan.getNombre());
                    plan.cargarMaterias(file);
                    System.out.printf("%s%d\n", "Plan cargado = \t\t\t", System.currentTimeMillis());
                    readPlanes.nextLine();
                }
            }
        } catch (FileNotFoundException ex) {
            Toast.makeText(this, "F", Toast.LENGTH_LONG).show();
        }*/

        /*try
        String text = planes.getTail().getElement().getSem();
        Toast.makeText(this, text, Toast.LENGTH_LONG).show(); {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.informacion_planes);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            Toast.makeText(this, new String(b), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // e.printStackTrace();
        }*/

        try {
            DataInputStream textFileStream = new DataInputStream(getAssets().open(String.format("informacion_planes.txt")));
            Scanner readPlanes = new Scanner(textFileStream);
            while (readPlanes.hasNext()) {
                Plan plan = new Plan(readPlanes.next(), readPlanes.nextInt(),
                        readPlanes.nextInt(), readPlanes.nextInt(), readPlanes.nextInt());
                planes.add(plan, planes.getSize());

                plan.cargarMaterias(new DataInputStream(getAssets().open(String.format(plan.getNombre() + ".txt"))));
                System.out.printf("%s%d\n", "Plan cargado = \t\t\t", System.currentTimeMillis());
                readPlanes.nextLine();
            }
            readPlanes.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "F", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
