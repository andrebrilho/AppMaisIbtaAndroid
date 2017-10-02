package com.example.brilho.maisibta;

import android.widget.EditText;

import com.example.brilho.maisibta.modelo.Materia;

/**
 * Created by Brilho on 07/05/2017.
 */

public class FormularioHelper {

    private final EditText campoMateria;
    private final EditText campoProfessor;
    private final EditText campoNotaProva1;
    private final EditText campoNotaProva2;
    private final EditText campoNotaEd;

    private Materia materia;

    public FormularioHelper(ForumalarioMaterias activity){

        //activity.findViewById(R.id.fomulario_materia)

       campoMateria = (EditText) activity.findViewById(R.id.fomulario_materia);
       campoProfessor = (EditText) activity.findViewById(R.id.fomulario_professor);
       campoNotaProva1 = (EditText) activity.findViewById(R.id.fomulario_nota_prova_1);
       campoNotaProva2 = (EditText) activity.findViewById(R.id.fomulario_nota_prova_2);
       campoNotaEd = (EditText) activity.findViewById(R.id.formulario_nota_ed);
       materia = new Materia();


    }

    public Materia pegaMateria() {



        materia.setMateria(campoMateria.getText().toString());
        materia.setProfessor(campoProfessor.getText().toString());
        materia.setNotaProva1(campoNotaProva1.getText().toString());
        materia.setNotaProva2(campoNotaProva2.getText().toString());
        materia.setNotaEd(campoNotaEd.getText().toString());

        return materia;
    }

    public void preencheFormulario(Materia materia){

        campoMateria.setText(materia.getMateria());
        campoProfessor.setText(materia.getProfessor());
        campoNotaProva1.setText(materia.getNotaProva1());
        campoNotaProva2.setText(materia.getNotaProva2());
        campoNotaEd.setText(materia.getNotaEd());

        this.materia = materia;

    }
}
