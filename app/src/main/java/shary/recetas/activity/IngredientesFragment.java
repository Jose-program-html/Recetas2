package shary.recetas.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shary.recetas.R;

/**
 * Created by Shary on 27/06/2015.
 */
public class IngredientesFragment extends Fragment {
    private String[] titles = {"Frutas", "Verduras", "Cereales", "Condimentos", "Carnes", "Lacteos", "Agregar", "Otros", "Busqueda"};

    public IngredientesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredientes, container, false);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        try {
            ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
            viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(),
                    rootView, titles, 1, 9));

            // Give the TabLayout the ViewPager
            TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
            tabLayout.setupWithViewPager(viewPager);
            // Inflate the layout for this fragment
            setRetainInstance(true);
        }catch (Exception e){
            e.printStackTrace();
        }
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

}
