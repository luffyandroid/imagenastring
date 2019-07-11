package com.example.imagenastring;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView cambio;
    EditText etcambio;
    ImageView imagen1, imagen2;
    Button imagenastring, stringaimagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cambio = (TextView)findViewById(R.id.cambio);
        etcambio = (EditText)findViewById(R.id.etcambio);
        imagen1= (ImageView)findViewById(R.id.imagen1);
        imagen2= (ImageView)findViewById(R.id.imagen2);
        imagenastring=(Button)findViewById(R.id.imagenastring);
        stringaimagen=(Button)findViewById(R.id.stringaimagen);

    }

    public void imagenaestring (View view){

        // give your image file url in mCurrentPhotoPath
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.descarga);
        //Bitmap bitmap1 = BitmapFactory.decodeResource(imagen1);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // En caso que quieras comprimir la imagen, aqui esta al 10%
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        //Damos el bytearray y lo codificamos en base64 para ponerlo en un string
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        //Ponemos el string en un textview (en este caso un edittext)
        etcambio.setText(encodedImage.toString());

    }

    public void stringaimagen (View view){

        //String de la imagen codificada
        String imageDataBytes = etcambio.getText().toString();

        InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
        //Convertimos el string a mapa de bits
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        //Ese mapa de bits lo ponemos en el imageview de destino
        imagen2.setImageBitmap(bitmap);

    }

    /*private String getBase64String() {

        // give your image file url in mCurrentPhotoPath
        Bitmap bitmap = BitmapFactory.decodeFile("R.drawable.descarga.jpg");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // In case you want to compress your image, here it's at 100%
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        cambio.setText(encodedImage.toString());

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }*/

}
