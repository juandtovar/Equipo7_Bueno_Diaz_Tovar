package com.example.unet.ui.MiPlan;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.unet.R;
import com.example.unet.data.BinaryTreeNode;
import com.example.unet.data.Chain;
import com.example.unet.data.Identificador;
import com.example.unet.data.Materia;
import com.example.unet.data.Plan;
import com.example.unet.logic.Conf;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MiPlanFragment extends Fragment {

    private String user;
    private MiPlanViewModel mViewModel;
    private Chain<Plan> planes = new Chain<>();
    private Plan planActual;
    private final int parametrosP = 7;
    private final int parametros = 6;
    private final int nPlanes = 2;

    public static MiPlanFragment newInstance() {
        return new MiPlanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mi_plan_fragment, container, false);
        System.out.println("Let the hunger games begin");
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Chain<Plan> temp = planes;
        while (temp.getSize() > 0) {
            temp.remove(temp.getSize() - 1);
        }
        planes = temp;
        int nPlan = Conf.getSpinner_plan().getSelectedItemPosition();
        InputStream file = null;
        switch (nPlan) {
            case 0:
                file = getResources().openRawResource(R.raw.ingenieria_mecatronica);
                System.out.println("MT");
                break;
            case 1:
                file = getResources().openRawResource(R.raw.ingenieria_mecanica);
                System.out.println("MC");
                break;
        }
        InputStream fileP = getResources().openRawResource(R.raw.informacion_planes);
        BufferedReader readP = new BufferedReader(new InputStreamReader(fileP));

        String[] lecturas = new String[500];
        BufferedReader read = new BufferedReader(new InputStreamReader(file));


        try {
            String linea = readP.readLine();
            lecturas = linea.split(" ");
        } catch (Exception ex) {

        }

        planActual = new Plan(lecturas[nPlan * parametrosP + 0], Integer.parseInt(lecturas[nPlan * parametrosP + 1]),
                Integer.parseInt(lecturas[nPlan * parametrosP + 2]), Integer.parseInt(lecturas[nPlan * parametrosP + 3]),
                Integer.parseInt(lecturas[nPlan * parametrosP + 4]), Integer.parseInt(lecturas[nPlan * parametrosP + 5]),
                Integer.parseInt(lecturas[nPlan * parametrosP + 6]));


        System.out.println(planActual.getNombre());

        try {
            String linea = read.readLine();
            lecturas = linea.split("/ ");
        } catch (Exception ex) {

        }
        for (int i = 0; i < planActual.getnMaterias(); i++) {
            Materia materia = new Materia(Integer.parseInt(lecturas[parametros * i + 0]),
                    lecturas[parametros * i + 1], Integer.parseInt(lecturas[parametros * i + 2]),
                    lecturas[parametros * i + 3], lecturas[parametros * i + 4],
                    Integer.parseInt(lecturas[parametros * i + 5]));
            if (materia.getSemestre() == 0) {
                planActual.getOptativas().add(materia);
            } else {
                if (planActual.getSemestres()[materia.getSemestre() - 1] == null) {
                    ArrayList<Materia> semestreLista = new ArrayList<>();
                    planActual.getSemestres()[materia.getSemestre() - 1] = semestreLista;
                    semestreLista.add(materia);
                } else {
                    planActual.getSemestres()[materia.getSemestre() - 1].add(materia);
                }
                Identificador identificador = new Identificador(materia.getCodigo(), materia.getSemestre(),
                        planActual.getSemestres()[materia.getSemestre() - 1].size() - 1);
                BinaryTreeNode<Identificador> tempNode = new BinaryTreeNode();
                System.out.println(identificador.toString());
                planActual.getIdentificadores().add(identificador);
            }
        }

        int n = planActual.getN_semestres(), m = planActual.getMaxMaterias();

        LinearLayout semestres = new LinearLayout(getActivity());
        semestres.setLayoutParams(param);
        semestres.setOrientation(LinearLayout.HORIZONTAL);
        Button dirButton[][] = new Button[n + 5][m + 2];

        for (int i = 0; i < n; i++) {
            LinearLayout L = new LinearLayout(getActivity());
            L.setOrientation(LinearLayout.VERTICAL);
            for (int j = 0; j < m; j++) {
                dirButton[i][j] = new Button(getActivity());
                dirButton[i][j].setLayoutParams(param2);
                try {
                    Materia mat = planActual.getSemestres()[i].get(j);
                    dirButton[i][j].setText(mat.getName());
                } catch (Exception ex) {
                    dirButton[i][j].setVisibility(View.INVISIBLE);
                }
                L.addView(dirButton[i][j]);
            }
            semestres.addView(L);
        }

        HorizontalScrollView vista = new HorizontalScrollView(getActivity());
        vista.addView(semestres);
        ViewGroup vg = (ViewGroup) rootView;
        vg.addView(vista);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MiPlanViewModel.class);
    }

}

