package com.example.unet.ui.MiPlan;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
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
import android.widget.ScrollView;

import com.example.unet.R;

public class MiPlanFragment extends Fragment{

    private MiPlanViewModel mViewModel;

    public static MiPlanFragment newInstance() {
        return new MiPlanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mi_plan_fragment, container, false);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int n = 10;
        int m = 15;

        LinearLayout semestres = new LinearLayout(getActivity());
        semestres.setLayoutParams(param);
        semestres.setOrientation(LinearLayout.VERTICAL);

        Button dirButton[] = new Button[n * m];

        for(int i = 0; i < n; i++) {
            LinearLayout L =  new LinearLayout(getActivity());
            for(int j = 0; j < m; j++) {
                dirButton[i] = new Button(getActivity());
                dirButton[i].setLayoutParams(param2);
                dirButton[i].setText(String.valueOf(i + 1).concat(" ".concat(String.valueOf(j + 1))));
                L.addView(dirButton[i]);
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

