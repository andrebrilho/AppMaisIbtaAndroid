package com.example.brilho.maisibta;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brilho.maisibta.dao.MateriaDao;
import com.example.brilho.maisibta.modelo.Materia;

import java.util.List;

public class ListaMateriasActivity extends Fragment {

    private ListView materias;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.lista_meterias, container, false);
       materias = (ListView) rootView.findViewById(R.id.meterias_lista);

        materias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Materia materia = (Materia) materias.getItemAtPosition(position);
                //Toast.makeText(getContext(), "Materia " + materia.getMateria() + " Clicada", Toast.LENGTH_SHORT).show();
                Intent intentAbreForm = new Intent(getActivity(), ForumalarioMaterias.class);
                intentAbreForm.putExtra("materia", materia);
                startActivity(intentAbreForm);
            }
        });

        Button novoMateria = (Button) rootView.findViewById(R.id.btn_materias_novo_aluno);
        novoMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chamaTelaFormMateria = new Intent(getActivity(), ForumalarioMaterias.class);
                startActivity(chamaTelaFormMateria);

            }
        });

        registerForContextMenu(materias);

        return rootView;
}

    private void CarregaLista(View rootView) {
        MateriaDao dao = new MateriaDao(getContext());
        List<Materia> materia = dao.buscaMateria();
        dao.close();


        ArrayAdapter<Materia> adp = new ArrayAdapter<Materia>(getContext(), android.R.layout.simple_list_item_1, materia);
        materias.setAdapter(adp);
    }

    @Override
    public void onResume() {
        super.onResume();
        CarregaLista(getView());

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Materia materia = (Materia) materias.getItemAtPosition(info.position);

                MateriaDao dao = new MateriaDao(getContext());
                dao.deleta(materia);
                dao.close();
                CarregaLista(getView());
                //Toast.makeText(getContext(), "Materia " + materia.getMateria() + " deletada", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
