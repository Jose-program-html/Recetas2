package shary.recetas.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import shary.recetas.R;


public class DecodeImage extends ActionBarActivity {
    private ImageView imagenDecode;
    String aux = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode_image);
        imagenDecode = (ImageView) findViewById(R.id.imagenDecode);
        Picasso.with(this).load("http://192.168.0.7:9000/assets/images/roles.jpg").into(imagenDecode);
    }
}
