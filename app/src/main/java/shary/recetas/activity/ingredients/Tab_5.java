package shary.recetas.activity.ingredients;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.List;

import shary.recetas.R;
import shary.recetas.activity.SQLite.ColumnsTable;
import shary.recetas.activity.SQLite.Querys;
import shary.recetas.activity.SQLite.Variables;

/**
 * Created by Shary on 04/07/2015.
 */
public class Tab_5 extends Fragment {
    private ColumnsTable columnsTable = new ColumnsTable();
    private static Variables variables = new Variables();
    private ListView ingredientsListView;
    public List<String> listado;
    public String carnes = "";
    SharedPreferences sharedPreferences;
    View rootView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab5_ingredients, container, false);
        ingredientsListView = (ListView) rootView.findViewById(R.id.ingredients_list_view);
        carnes();
        verificar();
        return rootView;
    }

    public void carnes() {
        try {
            Querys querys = new Querys(rootView.getContext(), "ingredients");
            querys.listadoDistict("nombre", 0, "clasificacion", "CARNES");
            listado = querys.lista;

            ArrayAdapter<String> itemsAdapter =
                    new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_multiple_choice, listado);
            ingredientsListView.setAdapter(itemsAdapter);
            ingredientsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            ingredientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CheckedTextView checkedTextView = ((CheckedTextView) view);
                    if (checkedTextView.isChecked() == true) {
                        verificar();
                    } else {
                        verificar();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void verificar() {
        try {
            carnes = "";
            for (int i = 0; i < listado.size(); i++) {
                if (ingredientsListView.getCheckedItemPositions().get(i) == true) {
                    carnes += ingredientsListView.getItemAtPosition(i).toString() + "-";
                }
            }
            variables.setCarne(carnes);
            System.out.println("carnes  " + variables.getCarne());
            sharedPreferences = rootView.getContext().getSharedPreferences("Ingredients", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Carnes", variables.getCarne());
            editor.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
