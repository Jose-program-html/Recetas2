package shary.recetas.activity.ingredients;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import shary.recetas.R;
import shary.recetas.activity.SQLite.ColumnsTable;
import shary.recetas.activity.SQLite.Querys;

/**
 * Created by Shary on 04/07/2015.
 */
public class Tab_Otros extends Fragment {
    private FloatingActionButton add;
    private EditText name;
    private EditText typeIng;
    public String cadena = "";
    private ColumnsTable columnsTable = new ColumnsTable();
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_others, container, false);
        name = (EditText) rootView.findViewById(R.id.etxt1);
        typeIng = (EditText) rootView.findViewById(R.id.etxt2);
        add = (FloatingActionButton) rootView.findViewById(R.id.btn_1);
        agregar();
        return rootView;
    }

    public void agregar() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ENTRO OTHER");
                cadena = name.getText().toString() + "-" + typeIng.getText().toString();
                name.setText("");
                typeIng.setText("");
                System.out.println(cadena);
                Querys querys = new Querys(rootView.getContext(), "ingredients_other");
                querys.insertar(columnsTable.getColumnsTableIngredientsOther(), cadena);
            }
        });
    }


}
