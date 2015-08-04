package shary.recetas.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import shary.recetas.R;
import shary.recetas.activity.SQLite.ColumnsTable;
import shary.recetas.activity.SQLite.Querys;

/**
 * Created by Shary on 27/06/2015.
 */
public class PasosFragment extends Fragment {
    private String[] titles = {"Ingredientes", "Instrucciones"};
    private String id;
    private ImageView imageView, imageView2;
    private TextView recipeTitle, recipeDificult;
    ColumnsTable columnsTable = new ColumnsTable();
    public List<String> listado;
    public List<String> listado2;
    public List<String> listado3;
    public List<String> listado4;
    SharedPreferences sharedPreferences;
    int fav = 0;
    View rootView;

    public PasosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_step, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.imageViewStep);
        recipeTitle = (TextView) rootView.findViewById(R.id.recipetitle);
        recipeDificult = (TextView) rootView.findViewById(R.id.recipedificult);
        imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
        getRecipe();
        if (listado4.get(0).toString().equals("0")) {
            Drawable myDrawable = getResources().getDrawable(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
            imageView2.setImageDrawable(myDrawable);
        } else {
            Drawable myDrawable = getResources().getDrawable(R.drawable.star24);
            imageView2.setImageDrawable(myDrawable);
        }

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager2);
        viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(),
                rootView, titles, 2, 2));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs2);
        tabLayout.setupWithViewPager(viewPager);

        // Inflate the layout for this fragment
        setRetainInstance(true);


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public String getIdRecipe() {
        try {
            SharedPreferences sharedPreferences = rootView.getContext().getSharedPreferences("Receta", Context.MODE_PRIVATE);
            id = sharedPreferences.getString("idReceta", "Nada");
            System.out.println("ID DE RECETA A BUSCAR " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void getRecipe() {
        querys();
        String url = getString(R.string.api_endpoint).concat(listado3.get(0).toString().toLowerCase());
        System.out.println("FAV " + listado4.get(0).toString());
        Picasso.with(rootView.getContext()).load(url).into(imageView);
        recipeTitle.setText(listado.get(0).toString());
        recipeDificult.setText(listado2.get(0).toString());

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    querys();
                    if (listado4.get(0).toString().equals("0")) {
                        Drawable myDrawable = getResources().getDrawable(R.drawable.star24);
                        imageView2.setImageDrawable(myDrawable);
                        Querys querys = new Querys(rootView.getContext(), "recipe");
                        querys.update("favorito", "1", 1, "id", getIdRecipe());
                        Toast.makeText(rootView.getContext(), recipeTitle.getText() + " Agregado a favoritos", Toast.LENGTH_SHORT).show();
                        System.out.println("FAV AMARILLO");
                    } else {
                        Querys querys = new Querys(rootView.getContext(), "recipe");
                        querys.update("favorito", "0", 1, "id", getIdRecipe());
                        Drawable myDrawable = getResources().getDrawable(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
                        imageView2.setImageDrawable(myDrawable);
                        Toast.makeText(rootView.getContext(), recipeTitle.getText() + " Retirado de favoritos", Toast.LENGTH_SHORT).show();
                        System.out.println("FAV ");
                    }
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        });
    }

    public void querys(){
        Querys querys = new Querys(rootView.getContext(), "recipe");
        querys.listado(columnsTable.getColumnsTableRecipe(), 1, "id", getIdRecipe());
        listado = querys.lista;
        listado2 = querys.lista1;
        listado3 = querys.lista2;
        listado4 = querys.lista3;
    }

}
