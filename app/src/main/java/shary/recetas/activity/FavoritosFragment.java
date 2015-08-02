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

/**
 * Created by Shary on 27/06/2015.
 */
public class FavoritosFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ColumnsTable columnsTable = new ColumnsTable();
    public List<String> listado;
    public List<String> listado2;
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favoritos, container, false);
        super.onCreate(savedInstanceState);
        recetas();
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

    public void recetas() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "favorito", "1");
        listado = querys.lista;
        listado2 = querys.lista1;
    }
}
