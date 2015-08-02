package shary.recetas.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shary.recetas.R;

/**
 * Created by Shary on 25/07/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RecetaViewHolder> {
    private List<RecetaAux> items;

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        ImageView recipePhoto;
        TextView recipeName;
        TextView recipeCategory;


        RecetaViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.nombre);
            recipeCategory = (TextView) itemView.findViewById(R.id.categoria);
            recipePhoto = (ImageView) itemView.findViewById(R.id.imagen);

        }

    }

    public RVAdapter(List<RecetaAux> items) {
        this.items = items;
    }

    @Override
    public RecetaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview, viewGroup, false);
        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) v.getContext()).getFragmentManager().beginTransaction().replace(R.id.container_body, new PasosFragment()).commit();
                ((ActionBarActivity) v.getContext()).getSupportActionBar().setTitle("Receta");
            }
        });*/
        return new RecetaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecetaViewHolder holder, int position) {
        holder.recipePhoto.setImageResource(items.get(position).getIdPhoto());
        holder.recipeName.setText(items.get(position).getName());
        holder.recipeCategory.setText(items.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
