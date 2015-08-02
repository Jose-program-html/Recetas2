package shary.recetas.activity.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Shary on 07/04/2015.
 */
public class Querys {
    public Context context;
    public String tableName;
    public String[] columns;
    public String[] values;
    public AdminSQLiteOpenHelper admin;
    public Variables variables;
    public List<String> lista, lista1,lista2;
    public String informacionEscuela = "";

    public Querys(Context context, String table) {
        this.context = context;
        this.tableName = table;
        admin = new AdminSQLiteOpenHelper(this.context,
                this.tableName, null, 1);
    }

    public void insertar(String[] columnas, String valor) {
        String[] valores = valor.split("-");
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        for (int i = 0; i < columnas.length; i++) {
            registro.put(columnas[i], valores[i]);
        }
        bd.insert(this.tableName, null, registro);
        System.out.println("Se inserto correctmente");
        bd.close();
    }

    public void truncate() {
        String selectQuery = "delete from " + this.tableName;
        SQLiteDatabase bd = admin.getWritableDatabase();
        bd.execSQL(selectQuery);
        bd.close();
    }


    public void listado(String[] columnas, int numColumna, String clausula, String valorClausula) {
        String dato;
        String[] valor = new String[columnas.length];
        lista = new ArrayList<String>();
        lista1 = new ArrayList<String>();
        lista2 = new ArrayList<String>();
        try {
            String selectQuery = "SELECT  *FROM " + this.tableName + " WHERE " + clausula + "=?";
            ;
            //SQLiteDatabase bd = admin.getWritableDatabase();
            //Cursor cursor = bd.rawQuery(selectQuery, null);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, new String[]{valorClausula});
            if (cursor.moveToFirst()) {
                do {
                    for (int i = 0; i < columnas.length; i++) {
                        valor[i] = cursor.getString(i);
                    }
                    lista.add(valor[numColumna]);
                    lista1.add(valor[3]);
                    lista1.add(valor[7]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        } catch (Exception e) {
        }
    }

    public void update(String columna, String nuevoValor, int numColumna, String clausula, String valorClausula) {
        String[] valor = new String[1];
        lista = new ArrayList<String>();
        try {
            String selectQuery = "UPDATE " + this.tableName + " SET " + columna + " = " + nuevoValor + " WHERE " + clausula + "=?";
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, new String[]{valorClausula});
            if (cursor.moveToFirst()) {
                do {
                    for (int i = 0; i < 1; i++) {
                        valor[i] = cursor.getString(i);
                    }
                    lista.add(valor[numColumna]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        } catch (Exception e) {
        }
    }


    public void listadoDistict(String columna, int numColumna, String clausula, String valorClausula) {
        String[] valor = new String[1];
        lista = new ArrayList<String>();
        try {
            String selectQuery = "SELECT DISTINCT " + columna + " FROM " + this.tableName + " WHERE " + clausula + "=?";
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, new String[]{valorClausula});
            if (cursor.moveToFirst()) {
                do {
                    for (int i = 0; i < 1; i++) {
                        valor[i] = cursor.getString(i);
                    }
                    lista.add(valor[numColumna]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        } catch (Exception e) {
        }
    }


    public void filtradoBusqueda(String columnas, String columna, int numColumna, String clausula, String valorClausula) {
        String cadenaClausula = "";
        String[] valor = new String[1];
        lista = new ArrayList<String>();
        String[] likes = columnas.split("-");
        for (int j = 0; j < likes.length; j++) {
            cadenaClausula += clausula + " LIKE '" + likes[j].toString() + "' OR ";
        }
        cadenaClausula = cadenaClausula.substring(0, cadenaClausula.length() - 3);
        System.out.println(cadenaClausula);
        try {
            String selectQuery = "SELECT DISTINCT " + columna + " FROM " + this.tableName + " WHERE " + cadenaClausula;
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    for (int i = 0; i < 1; i++) {
                        valor[i] = cursor.getString(i);
                    }
                    lista.add(valor[numColumna]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        } catch (Exception e) {
        }
    }

    public void listadoDistictNotClause(String columna, int numColumna) {
        String[] valor = new String[1];
        lista = new ArrayList<String>();
        try {

            String selectQuery = "SELECT DISTINCT " + columna + " FROM " + this.tableName;
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    for (int i = 0; i < 1; i++) {
                        valor[i] = cursor.getString(i);
                    }
                    lista.add(valor[numColumna]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        } catch (Exception e) {
        }
    }
}

