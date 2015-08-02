package shary.recetas.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import shary.recetas.activity.ingredients.Tab_1;
import shary.recetas.activity.ingredients.Tab_2;
import shary.recetas.activity.ingredients.Tab_3;
import shary.recetas.activity.ingredients.Tab_4;
import shary.recetas.activity.ingredients.Tab_5;
import shary.recetas.activity.ingredients.Tab_6;
import shary.recetas.activity.ingredients.Tab_Busqueda;
import shary.recetas.activity.ingredients.Tab_Other_Ingredient;
import shary.recetas.activity.ingredients.Tab_Otros;
import shary.recetas.activity.recipes.Tab_Recipes;
import shary.recetas.activity.step.Tab_1_Step;
import shary.recetas.activity.step.Tab_2_Step;

/**
 * Created by Shary on 04/07/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int PAGE_COUNT;
    private String[] tabTitles;
    private View context;
    private int positionNav = 0;
    public Tab_Recipes tab_Recipes;
    public Recycler recycler;
    Tab_1 tab_1;
    Tab_2 tab_2;
    Tab_3 tab_3;
    Tab_4 tab_4;
    Tab_5 tab_5;
    Tab_6 tab_6;
    Tab_Otros tab_otros;
    Tab_Other_Ingredient tab_other_ingredient;
    Tab_Busqueda tab_busqueda;
    Tab_1_Step tab_1_step;
    Tab_2_Step tab_2_step;

    public ViewPagerAdapter(FragmentManager fm, View context, String[] titles, int position, int page) {
        super(fm);
        this.context = context;
        this.tabTitles = titles;
        this.positionNav = position;
        this.PAGE_COUNT = page;

        tab_1 = new Tab_1();
        tab_2 = new Tab_2();
        tab_3 = new Tab_3();
        tab_4 = new Tab_4();
        tab_5 = new Tab_5();
        tab_6 = new Tab_6();
        tab_otros = new Tab_Otros();
        tab_other_ingredient = new Tab_Other_Ingredient();
        tab_busqueda = new Tab_Busqueda();
        tab_1_step = new Tab_1_Step();
        tab_2_step = new Tab_2_Step();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("POSICION VIEW RECIPE " + position);
        if (positionNav == 0) {
            switch (position) {
                case 0:
                    //recycler = new Recycler();
                    recycler = Recycler.newInstance(0);
                    return recycler;
                case 1:
                    //recycler = new Recycler();
                    recycler = Recycler.newInstance(1);
                    return recycler;
                case 2:
                    //recycler = new Recycler();
                    recycler = Recycler.newInstance(2);
                    return recycler;
                case 3:
                    //recycler = new Recycler();
                    recycler = Recycler.newInstance(3);
                    return recycler;
                case 4:
                    //recycler = new Recycler();
                    recycler = Recycler.newInstance(4);
                    return recycler;
                default:
                    break;
            }
        } else if (positionNav == 1) {
            switch (position) {
                case 0:

                    return tab_1;
                case 1:

                    return tab_2;
                case 2:

                    return tab_3;
                case 3:

                    return tab_4;
                case 4:

                    return tab_5;
                case 5:

                    return tab_6;
                case 6:
                    return tab_otros;
                case 7:
                    return tab_other_ingredient;
                case 8:
                    return tab_busqueda;
                default:
                    break;
            }
        } else {
            switch (position) {
                case 0:
                    return tab_1_step;
                case 1:
                    return tab_2_step;
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}