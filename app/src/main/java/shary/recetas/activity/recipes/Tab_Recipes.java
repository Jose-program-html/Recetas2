package shary.recetas.activity.recipes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import shary.recetas.R;
import shary.recetas.activity.PasosFragment;
import shary.recetas.activity.SQLite.ColumnsTable;
import shary.recetas.activity.SQLite.Querys;

/**
 * Created by Shary on 04/07/2015.
 */
public class Tab_Recipes extends Fragment {
    private ColumnsTable columnsTable = new ColumnsTable();
    private ListView recipeListView;
    public List<String> listado;
    View rootView;


    public static Tab_Recipes newInstance(int i) {
        Tab_Recipes myFragment = new Tab_Recipes();
        Bundle args = new Bundle();
        args.putInt("someInt", i);
        myFragment.setArguments(args);
        System.out.println("AQUI CONTRUCTOR " + i);
        return myFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_recipes, container, false);
        recipeListView = (ListView) rootView.findViewById(R.id.recipes_list_view);
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
        return rootView;
    }

    public void getIdRecipe(int position) {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 0, "nombre", recipeListView.getItemAtPosition(position).toString());
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

        //getFragmentManager().beginTransaction().replace(R.id.container_body, new HomeFragment()).commit();
        ((ActionBarActivity) rootView.getContext()).getSupportActionBar().setTitle("Receta");
    }

    public void desayuno() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "DESAYUNO");
        listado = querys.lista;

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(rootView.getContext(),android.R.drawable.star_big_on, android.R.layout.simple_list_item_1, listado);
        recipeListView.setAdapter(itemsAdapter);
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIdRecipe(position);
                getInstruccions();
            }
        });
    }

    public void pasta() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "PASTA");
        listado = querys.lista;

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, listado);
        recipeListView.setAdapter(itemsAdapter);
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIdRecipe(position);
                getInstruccions();
            }
        });
    }

    public void entrada() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "ENTRADA");
        listado = querys.lista;

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, listado);
        recipeListView.setAdapter(itemsAdapter);
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIdRecipe(position);
                getInstruccions();
            }
        });
    }

    public void platoFuerte() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "PLATO FUERTE");
        listado = querys.lista;

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, listado);
        recipeListView.setAdapter(itemsAdapter);
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIdRecipe(position);
                getInstruccions();
            }
        });
    }

    public void postre() {
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "tipo", "POSTRE");
        listado = querys.lista;

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, listado);
        recipeListView.setAdapter(itemsAdapter);
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getIdRecipe(position);
                getInstruccions();
            }
        });
    }
}