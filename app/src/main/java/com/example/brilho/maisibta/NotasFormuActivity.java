package com.example.brilho.maisibta;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NotasFormuActivity extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_nota, container, false);

        Button btnCalculaMedia = (Button) rootView.findViewById(R.id.formulario_btnCalcMedia);
        btnCalculaMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Calculando Media", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
