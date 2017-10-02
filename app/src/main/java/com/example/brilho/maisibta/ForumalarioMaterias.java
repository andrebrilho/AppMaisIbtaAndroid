package com.example.brilho.maisibta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brilho.maisibta.dao.MateriaDao;
import com.example.brilho.maisibta.modelo.Materia;

import java.sql.BatchUpdateException;

public class ForumalarioMaterias extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nota);

        Intent intent = getIntent();
        final Materia materia = (Materia) intent.getSerializableExtra("materia");
        helper = new FormularioHelper(this);

        if (materia != null){
            helper.preencheFormulario(materia);
        }

        Button btnSalvar = (Button) findViewById(R.id.formulario_btnSalvar);
        Button btnCalculamedia = (Button) findViewById(R.id.formulario_btnCalcMedia);

        final TextView txtNota1 = (TextView)findViewById(R.id.fomulario_nota_prova_1);
        final TextView txtNota2 = (TextView)findViewById(R.id.fomulario_nota_prova_2);
        final TextView txtNotaED = (TextView)findViewById(R.id.formulario_nota_ed);







        btnCalculamedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer nota1 = Integer.parseInt(txtNota1.getText().toString());
                Integer nota2 = Integer.parseInt(txtNota2.getText().toString());
                Integer ed = Integer.parseInt(txtNotaED.getText().toString());
                Integer media = (nota1 + nota2 + ed) / 3 ;
                String textRetornoMediaSucesso = "Parabens Você foi aprovado com média ";

                if (media >= 7){

                    Toast.makeText(ForumalarioMaterias.this, textRetornoMediaSucesso + media, Toast.LENGTH_SHORT).show();

                } else

                    Toast.makeText(ForumalarioMaterias.this, "Reprovado :( ", Toast.LENGTH_SHORT).show();


            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Materia materia = helper.pegaMateria();
                MateriaDao dao = new MateriaDao(ForumalarioMaterias.this);
                if (materia.getId() != null ){
                    dao.altera(materia);
                } else {
                    dao.insere(materia);
                }
                dao.close();
                Toast.makeText(ForumalarioMaterias.this, "Materia " + materia.getMateria() + " salvo", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
