package shary.recetas.activity.ingredients;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import shary.recetas.R;
import shary.recetas.activity.RecyclerRecetaBusqueda;
import shary.recetas.activity.SQLite.Variables;

/**
 * Created by Shary on 04/07/2015.
 */
public class Tab_Busqueda extends Fragment {
    private TextView listadoIng;
    private FloatingActionButton buscar;
    static Variables variables = new Variables();
    String busquedaF;
    String busquedaV;
    String busquedaC;
    String busquedaCo;
    String busquedaCa;
    String busquedaL;
    String busquedaO;
    View rootView;
    String aux = "";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_search_ingredients, container, false);
        listadoIng = (TextView) rootView.findViewById(R.id.txtsearch);
        buscar = (FloatingActionButton) rootView.findViewById(R.id.btn_search);
        try {
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
            if (!busqueda.equals("")) {
                busqueda = busqueda.replace("-", ", ");
                busqueda = busqueda.substring(0, busqueda.length() - 2);
                listadoIng.setText(busqueda);
                buscar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            Fragment fragment = new RecyclerRecetaBusqueda();
                            fragmentTransaction.replace(R.id.container_body, fragment);
                            fragmentTransaction.commit();
                            ((ActionBarActivity) rootView.getContext()).getSupportActionBar().setTitle("Busqueda Receta");
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(rootView.getContext(), "No hay ingredientes para buscar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else {
                busqueda = "Nada";
                listadoIng.setText(busqueda);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rootView;
    }
}
