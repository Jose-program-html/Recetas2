package shary.recetas.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
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
import shary.recetas.activity.SQLite.Variables;

/**
 * Created by Shary on 25/07/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RecetaViewHolder> {
    private List<RecetaAux> items;
    RecyclerView recyclerView;
    View context;
    public String recipeNemeAux = "";
    ColumnsTable columnsTable = new ColumnsTable();
    Variables variables = new Variables();
    public List<String> listado;

    public RVAdapter(List<RecetaAux> items, View context) {
        this.items = items;
        this.context=context;
    }

    @Override
    public RecetaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview, viewGroup, false);
        return new RecetaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecetaViewHolder holder, int position) {
        Picasso.with(context.getContext()).load(items.get(position).getPhoto()).into(holder.recipePhoto);
        holder.recipeName.setText(items.get(position).getName());
        holder.recipeCategory.setText(items.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecetaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView recipePhoto;
        TextView recipeName;
        TextView recipeCategory;

        public RecetaViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.nombre);
            recipeCategory = (TextView) itemView.findViewById(R.id.categoria);
            recipePhoto = (ImageView) itemView.findViewById(R.id.imagen);
            itemView.setTag(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int vId = v.getId();
            Toast.makeText(itemView.getContext(), items.get(getPosition()).getName() + "", Toast.LENGTH_SHORT).show();
            Querys querys = new Querys(v.getContext(), "recipe");
            querys.listado(columnsTable.getColumnsTableRecipe(), 0, "nombre", items.get(getPosition()).getName());
            listado = querys.lista;
            System.out.println("AQUI ID RECETA " + listado.get(0).toString());
            SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("Receta", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("idReceta", listado.get(0).toString());
            editor.commit();
            getInstruccions(v);
        }

    }

    public void getInstruccions(View v) {
        ((Activity) v.getContext()).getFragmentManager().beginTransaction().replace(R.id.container_body, new PasosFragment()).commit();
        ((ActionBarActivity) v.getContext()).getSupportActionBar().setTitle("Receta");

    }

}
