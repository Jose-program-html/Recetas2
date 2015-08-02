package shary.recetas.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import shary.recetas.R;
import shary.recetas.activity.SQLite.ColumnsTable;
import shary.recetas.activity.SQLite.Querys;

/**
 * Created by Shary on 27/06/2015.
 */
public class RecetasBusquedaFragment extends Fragment {
    private ColumnsTable columnsTable = new ColumnsTable();
    private ListView ingredientsListView;
    public List<String> listado;
    public List<String> listado2;
    private ActionBar ab;
    private String id;
    View rootView;
    String busquedaF;
    String busquedaV;
    String busquedaC;
    String busquedaCo;
    String busquedaCa;
    String busquedaL;
    String busquedaO;
    public String auxBusqueda = "";


    public RecetasBusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recetas_busqueda, container, false);
        ingredientsListView = (ListView) rootView.findViewById(R.id.recipes_list_view);


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
        // Inflate the layout for this fragment
        return rootView;
    }

    public void ingredientes() {
        Querys querys = new Querys(rootView.getContext(), "ingredients");
        querys.filtradoBusqueda(auxBusqueda, "recipe_id", 0, "nombre", auxBusqueda);
        listado = querys.lista;

        ArrayList<String> array3 = new ArrayList<String>(listado.size());
        for (int i = 0; i < listado.size(); i++) {
            querys = new Querys(rootView.getContext(), "recipe");
            querys.listado(columnsTable.getColumnsTableRecipe(), 1, "id", listado.get(i).toString());
            listado2 = querys.lista;
            array3.add(listado2.get(0).toString());
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, array3);
        ingredientsListView.setAdapter(itemsAdapter);
        ingredientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIdRecipe(position);
                getInstruccions();
            }
        });
    }

    public void getIdRecipe(int position) {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 0, "nombre", ingredientsListView.getItemAtPosition(position).toString());
        listado = querys.lista;
        System.out.println("AQUI ID RECETA " + listado.get(0).toString());
        SharedPreferences sharedPreferences = rootView.getContext().getSharedPreferences("Receta", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idReceta", listado.get(0).toString());
        editor.commit();
    }

    public void getInstruccions() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new PasosFragment();
        fragmentTransaction.add(R.id.container_body, fragment);
        fragmentTransaction.commit();


        //rootView.getContext().getSupportActionBar().setTitle("Busqueda Receta");
        ((ActionBarActivity) rootView.getContext()).getSupportActionBar().setTitle("Receta");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
