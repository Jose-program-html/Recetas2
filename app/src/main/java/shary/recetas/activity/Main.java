package shary.recetas.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import shary.recetas.R;
import shary.recetas.activity.SQLite.AdminSQLiteOpenHelper;
import shary.recetas.activity.SQLite.Records;

/**
 * Created by Shary on 29/06/2015.
 */
public class Main extends ActionBarActivity {
    private Toolbar toolbar;
    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    String endpoint;
    public String titulo = "";
    private ActionBar ab;
    Fragment fragment = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AdminSQLiteOpenHelper dbHelper = new AdminSQLiteOpenHelper(this,
                "recipes", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        System.out.println("CONECTION " + checkConnectivity());
        if (checkConnectivity() == true) {
            endpoint = getString(R.string.api_endpoint);
            Records records = new Records(this, endpoint);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            fragment = new HomeFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Home");
        }

        navigationView = (NavigationView) findViewById(R.id.naview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                try {
                    int x = menuItem.getItemId();
                    String title = menuItem.getTitle().toString();
                    titulo = title;
                    switch (x) {
                        case R.id.navigation_sub_item_1:
                            title = getString(R.string.title_home);
                            Snackbar.make(findViewById(android.R.id.content), title, Snackbar.LENGTH_SHORT).show();
                            fragment = new HomeFragment();
                            break;
                        case R.id.navigation_sub_item_2:
                            title = getString(R.string.title_recetas);
                            Snackbar.make(findViewById(android.R.id.content), title, Snackbar.LENGTH_SHORT).show();
                            fragment = new RecetasFragment();
                            break;
                        case R.id.navigation_sub_item_3:
                            title = getString(R.string.title_ingredientes);
                            Snackbar.make(findViewById(android.R.id.content), title, Snackbar.LENGTH_SHORT).show();
                            fragment = new IngredientesFragment();
                            break;
                        case R.id.navigation_sub_item_4:
                            title = getString(R.string.title_favoritos);
                            Snackbar.make(findViewById(android.R.id.content), title, Snackbar.LENGTH_SHORT).show();
                            fragment = new FavoritosFragment();
                            break;
                        default:
                            break;
                    }

                    if (fragment != null) {

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle(title);
                    }

                    Log.d("MENU ITEM", menuItem.getTitle().toString());
                    drawerLayout.closeDrawers();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        try {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            switch (item.getItemId()) {
                case android.R.id.home:
                    drawerLayout.openDrawer(GravityCompat.START);
                    return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean checkConnectivity() {
        boolean isConnected = false;
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }catch (Exception e){
            e.printStackTrace();
        }
        return isConnected;
    }

}