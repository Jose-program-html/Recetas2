package shary.recetas.activity;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
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


public class RecyclerRecetaBusqueda extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ColumnsTable columnsTable = new ColumnsTable();
    public List<String> listado;
    public List<String> listado2;
    public List<String> listado3;
    public List<String> listado4;
    ArrayList<String> array3, array4, array5;

    String busquedaF;
    String busquedaV;
    String busquedaC;
    String busquedaCo;
    String busquedaCa;
    String busquedaL;
    String busquedaO;
    public String auxBusqueda = "";

    View rootView;

    public RecyclerRecetaBusqueda() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recetas_busqueda, container, false);
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = rootView.getContext().getSharedPreferences("Ingredients", Context.MODE_PRIVATE);
        busquedaF = sharedPreferences.getString("Frutas", "Nada");
        busquedaV = sharedPreferences.getString("Verduras", "Nada");
        busquedaC = sharedPreferences.getString("Cereales", "Nada");
        busquedaCo = sharedPreferences.getString("Condimentos", "Nada");
        busquedaCa = sharedPreferences.getString("Carnes", "Nada");
        busquedaL = sharedPreferences.getString("Lacteos", "Nada");
        busquedaO = sharedPreferences.getString("Extras", "Nada");
        System.out.println("otros tab busqueda " + busquedaO);

        String busqueda = busquedaF
                + busquedaV
                + busquedaC
                + busquedaCo
                + busquedaCa
                + busquedaL
                + busquedaO;

        auxBusqueda = busqueda.substring(0, busqueda.length() - 1);
        System.out.println("Busqueda receta " + auxBusqueda);
        ingredientes();

        System.out.println("TOTAL " + listado.size());
        List items = new ArrayList();
        for (int i = 0; i < array3.size(); i++) {
            String url = getString(R.string.api_endpoint).concat(array5.get(i).toString().toLowerCase());
            System.out.println("URL " + url);
            items.add(new RecetaAux(url, array3.get(i).toString(), array4.get(i).toString()));
        }


        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RVAdapter(items, rootView);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }


    public void ingredientes() {
        Querys querys = new Querys(rootView.getContext(), "ingredients");
        querys.filtradoBusqueda(auxBusqueda, "recipe_id", 0, "nombre", auxBusqueda);
        listado = querys.lista;

        array3 = new ArrayList<String>(listado.size());
        array4 = new ArrayList<String>(listado.size());
        array5 = new ArrayList<String>(listado.size());
        for (int i = 0; i < listado.size(); i++) {
            querys = new Querys(rootView.getContext(), "recipe");
            querys.listado(columnsTable.getColumnsTableRecipe(), 1, "id", listado.get(i).toString());
            listado2 = querys.lista;
            listado3 = querys.lista1;
            listado4 = querys.lista2;
            array3.add(listado2.get(0).toString());
            array4.add((listado3.get(0).toString()));
            array5.add((listado4.get(0).toString()));
        }
    }
}
