package shary.recetas.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import shary.recetas.R;


public class DecodeImage extends ActionBarActivity {
    private ImageView imagenDecode;
    String aux = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode_image);
        imagenDecode = (ImageView) findViewById(R.id.imagenDecode);
        decodeBase64();
    }

    public String encodeTobase64() {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.logorecetasp3);
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        aux = imageEncoded;
        Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }

    public void decodeBase64() {
        String base64 = "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";
        byte[] decodedByteString = Base64.decode(base64, 0);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedByteString, 0, decodedByteString.length);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        decodedByte.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        try {

            //you can create a new file name "test.jpg" in sdcard folder.
            File f = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "test.jpg");
            f.createNewFile();
            //write the bytes in file
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());

            // remember close de FileOutput
            fo.close();

            imagenDecode.setImageBitmap(decodedByte);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
