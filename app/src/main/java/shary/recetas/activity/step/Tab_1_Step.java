package shary.recetas.activity.step;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import shary.recetas.R;
import shary.recetas.activity.RecetaAux;
import shary.recetas.activity.SQLite.ColumnsTable;
import shary.recetas.activity.SQLite.Querys;
import shary.recetas.activity.ViewPagerAdapter;

/**
 * Created by Shary on 04/07/2015.
 */
public class Tab_1_Step extends Fragment implements TextToSpeech.OnInitListener{
    private static final int MY_DATA_CHECK_CODE = 1234;
    private static final String TAG = "TextToSpeechDemo";
    private ColumnsTable columnsTable = new ColumnsTable();
    private ListView ingredientsListView;
    protected TextToSpeech t1;
    public List<String> listado;
    public List<String> listado2;
    private ActionBar ab;
    private String id;
    public TextView textView;
    public TextView textView2;
    public View dialogView;
    public AlertDialog.Builder dialogBuilder;
    public LayoutInflater inflater2;
    public AlertDialog dlg;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab1_step, container, false);
        ingredientsListView = (ListView) rootView.findViewById(R.id.ingredients_list_view);
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
        dialogBuilder = new AlertDialog.Builder(this.getActivity());
        inflater2 = this.getLayoutInflater(savedInstanceState);
        //set up button
        FloatingActionButton myFab = (FloatingActionButton)  rootView.findViewById(R.id.btn_voice);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert();
                Locale loc = new Locale("spa", "MEX");
                t1.setLanguage(loc);
                Querys querys = new Querys(rootView.getContext(), "ingredients");
                querys.listado(columnsTable.getColumnsTableIngredients(), 1, "recipe_id", getIdRecipe());
                listado = querys.lista;
                String texto = "";
                for (int i = 0; i < listado.size(); i++) {
                    texto += listado.get(i).toLowerCase();
                    if(i!=listado.size()){
                        texto +="\n";
                    }
                }
                textView2.setText(texto);
                t1.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
                dlg = dialogBuilder.show();
            }
        });
        ingredientes();
        return rootView;
    }

    public String getIdRecipe() {
        SharedPreferences sharedPreferences = rootView.getContext().getSharedPreferences("Receta", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("idReceta", "Nada");
        System.out.println("ID DE RECETA A BUSCAR " + id);
        return id;
    }

    public void alert(){
        dialogView = inflater2.inflate(R.layout.ttsdialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        textView = (TextView) dialogView.findViewById(R.id.txtviewdiagtitle);
        textView.setText("Ingredientes");
        textView2 = (TextView) dialogView.findViewById(R.id.txtviewdiagtext);
        Button btn = (Button) dialogView.findViewById(R.id.btndiag);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.stop();
                dlg.dismiss();
            }
        });
    }

    public void ingredientes() {
        Querys querys = new Querys(rootView.getContext(), "ingredients");
        querys.listado(columnsTable.getColumnsTableIngredients(), 1, "recipe_id", getIdRecipe());
        listado = querys.lista;
        querys.listado(columnsTable.getColumnsTableIngredients(), 2, "recipe_id", getIdRecipe());
        listado2 = querys.lista;
        ArrayList<String> array3 = new ArrayList<String>(listado.size());
        for (int i = 0; i < listado.size(); i++) {
            array3.add(listado2.get(i) + " " + listado.get(i));
        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, array3);
        ingredientsListView.setAdapter(itemsAdapter);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == MY_DATA_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                // success, create the TTS instance
                t1 = new TextToSpeech(getActivity(), this);
                t1.setLanguage(Locale.getDefault());
            }
            else
            {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    @Override
    public void onDestroy()
    {
        // Don't forget to shutdown!
        if (t1 != null)
        {
            t1.stop();
            t1.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            t1.setLanguage(Locale.getDefault());
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }
}
