package shary.recetas.activity.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shary on 17/03/2015.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipes.db";

    private static final String TABLE_RECIPE = "recipe";
    private static final String RECIPE_ID = "id";
    private static final String RECIPE_NOMBRE = "nombre";
    private static final String RECIPE_TIPO = "tipo";
    private static final String RECIPE_CATEGORIA = "categoria";
    private static final String RECIPE_DURACION = "duracion";
    private static final String RECIPE_PORCION = "porcion";
    private static final String RECIPE_IMG = "nombreimagen";
    private static final String RECIPE_BASE64 = "base64";
    private static final String RECIPE_FAV = "favorito";


    private static final String TABLE_INGREDIENTS_O = "ingredients_other";

    private static final String TABLE_INGREDIENTS = "ingredients";
    private static final String INGREDIENTS_ID = "id";
    private static final String INGREDIENTS_NOMBRE = "nombre";
    private static final String INGREDIENTS_CANTIDAD = "cantidad";
    private static final String INGREDIENTS_CLASIF = "clasificacion";
    private static final String INGREDIENTS_IDREC = "recipe_id";


    private static final String TABLE_STEP = "step";
    private static final String STEP_ID = "id";
    private static final String STEP_PASO = "paso";
    private static final String STEP_DESCRIPCION = "paso_descripcion";
    private static final String STEP_ID_RECETA = "id_step_recipe_id";


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_RECIPE_TABLE = "CREATE TABLE " + TABLE_RECIPE + "("
                + RECIPE_ID + " INTEGER PRIMARY KEY," + RECIPE_NOMBRE + " TEXT,"
                + RECIPE_TIPO + " TEXT,"
                + RECIPE_CATEGORIA + " TEXT,"
                + RECIPE_DURACION + " TEXT,"
                + RECIPE_PORCION + " INTEGER,"
                + RECIPE_IMG + " TEXT,"
                + RECIPE_BASE64 + " TEXT,"
                + RECIPE_FAV + " INTEGER" + ")";

        String CREATE_INGREDIENTS_TABLE = "CREATE TABLE " + TABLE_INGREDIENTS + "("
                + INGREDIENTS_ID + " INTEGER PRIMARY KEY,"
                + INGREDIENTS_NOMBRE + " TEXT,"
                + INGREDIENTS_CANTIDAD + " TEXT,"
                + INGREDIENTS_CLASIF + " TEXT,"
                + INGREDIENTS_IDREC + " INTEGER" + ")";

        String CREATE_INGREDIENTS_TABLE_O = "CREATE TABLE " + TABLE_INGREDIENTS_O + "("
                + INGREDIENTS_NOMBRE + " TEXT PRIMARY KEY,"
                + INGREDIENTS_CLASIF + " TEXT" + ")";


        String CREATE_STEP_TABLE = "CREATE TABLE " + TABLE_STEP + "("
                + STEP_ID + " INTEGER PRIMARY KEY,"
                + STEP_PASO + " INTEGER,"
                + STEP_DESCRIPCION + " TEXT,"
                + STEP_ID_RECETA + " INTEGER" + ")";


        db.execSQL(CREATE_RECIPE_TABLE);
        db.execSQL(CREATE_INGREDIENTS_TABLE);
        db.execSQL(CREATE_INGREDIENTS_TABLE_O);
        db.execSQL(CREATE_STEP_TABLE);
        System.out.println("SE CREO DB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS_O);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STEP);
        onCreate(db);
    }
}
