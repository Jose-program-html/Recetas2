package shary.recetas.activity;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import shary.recetas.R;
import shary.recetas.activity.SQLite.ColumnsTable;
import shary.recetas.activity.SQLite.Querys;


public class Recycler extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ColumnsTable columnsTable = new ColumnsTable();
    public List<String> listado;
    public List<String> listado2;
    View rootView;

    public static Recycler newInstance(int i) {
        Recycler recycler = new Recycler();
        Bundle args = new Bundle();
        args.putInt("someInt", i);
        recycler.setArguments(args);
        System.out.println("AQUI TIPO " + i);
        return recycler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_recicler, container, false);
        super.onCreate(savedInstanceState);

        switch (getArguments().getInt("someInt")) {
            case 0:
                desayuno();
                break;
            case 1:
                pasta();
                break;
            case 2:
                entrada();
                break;
            case 3:
                platoFuerte();
                break;
            case 4:
                postre();
                break;
        }


        System.out.println("TOTAL " + listado.size());
        List items = new ArrayList();
        for (int i = 0; i < listado.size(); i++) {
            items.add(new RecetaAux(R.drawable.favourite24, listado.get(i).toString(), listado2.get(i).toString()));
        }


        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RVAdapter(items, recyclerView);
        recyclerView.setAdapter(adapter);
        return rootView;
    }


    public void desayuno() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "DESAYUNO");
        listado = querys.lista;
        listado2 = querys.lista1;
    }

    public void pasta() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "PASTA");
        listado = querys.lista;
        listado2 = querys.lista1;
    }

    public void entrada() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "ENTRADA");
        listado = querys.lista;
        listado2 = querys.lista1;
    }

    public void platoFuerte() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "PLATO FUERTE");
        listado = querys.lista;
        listado2 = querys.lista1;
    }

    public void postre() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "POSTRE");
        listado = querys.lista;
        listado2 = querys.lista1;
    }
}
