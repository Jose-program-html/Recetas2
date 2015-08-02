package shary.recetas.activity;

import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
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
            getSupportFragmentManager().beginTransaction().replace(R.id.container_body, new HomeFragment()).commit();

        }

        navigationView = (NavigationView) findViewById(R.id.naview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int x = menuItem.getItemId();
                String title = menuItem.getTitle().toString();
                titulo = title;
                try {
                    Fragment tab1 = getFragmentManager().findFragmentById(R.id.ingredients_list_view);
                    Fragment tab2 = getFragmentManager().findFragmentById(R.id.instruccion_list_view);
                    if(tab1 != null || tab2 != null) {
                        getFragmentManager().beginTransaction().remove(tab1).commit();
                        getFragmentManager().beginTransaction().remove(tab2).commit();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                switch (x) {
                    case R.id.navigation_sub_item_1:
                        title = getString(R.string.title_home);
                        Snackbar.make(findViewById(android.R.id.content), title, Snackbar.LENGTH_SHORT).show();

                        getSupportFragmentManager().beginTransaction().replace(R.id.container_body, new HomeFragment()).commit();
                        getSupportActionBar().setTitle(title);
                        break;
                    case R.id.navigation_sub_item_2:

                        title = getString(R.string.title_recetas);
                        Snackbar.make(findViewById(android.R.id.content), title, Snackbar.LENGTH_SHORT).show();

                        getSupportFragmentManager().beginTransaction().replace(R.id.container_body, new RecetasFragment()).commit();
                        getSupportActionBar().setTitle(title);
                        break;
                    case R.id.navigation_sub_item_3:

                        title = getString(R.string.title_ingredientes);
                        Snackbar.make(findViewById(android.R.id.content), title, Snackbar.LENGTH_SHORT).show();

                        getSupportFragmentManager().beginTransaction().replace(R.id.container_body, new IngredientesFragment()).commit();
                        getSupportActionBar().setTitle(title);
                        break;
                    case R.id.navigation_sub_item_4:

                        title = getString(R.string.title_favoritos);
                        Snackbar.make(findViewById(android.R.id.content), title, Snackbar.LENGTH_SHORT).show();

                        getSupportFragmentManager().beginTransaction().replace(R.id.container_body, new FavoritosFragment()).commit();
                        getSupportActionBar().setTitle(title);
                        break;
                    default:
                        break;
                }

                Log.d("MENU ITEM", menuItem.getTitle().toString());
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean checkConnectivity() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}
