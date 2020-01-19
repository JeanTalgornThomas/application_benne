package com.example.mybenne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button modifierInfoUtilisateur;
    Button modifierInfoDestinataire;
    Button MAP;
    Button picture;
    Button cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // picture = findViewById(R.id.buttonTakePicture);
        cam = findViewById(R.id.buttonScanBarcode);

        modifierInfoUtilisateur = findViewById(R.id.modifierInfoUtilisateur);
        modifierInfoDestinataire = findViewById(R.id.modifierInfoDestinataire);
        MAP = findViewById(R.id.MAP);

        /*picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pict();
            }
        });*/

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera();
            }
        });

        modifierInfoUtilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierInfoUtilisateur();
            }
        });
        modifierInfoDestinataire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierInfoDestinataire();
            }
        });

        MAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Geolocalisation.class));
            }
        });
    }

    /*protected void pict()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, PictureBarcodeActivity.class);

        Intent intent2 = getIntent();
        if (intent2!= null)
        {
            Bundle extras2 = intent2.getExtras();
            if (extras2 != null)
            {
                intent1.putExtra("Nom", extras2.getString("Nom"));
            }
            startActivity(intent1);
        }
    }*/
    protected void camera()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, ScannedBarcodeActivity.class);

        Intent intent2 = getIntent();
        if (intent2!= null)
        {
            Bundle extras2 = intent2.getExtras();
            if (extras2 != null)
            {
                intent1.putExtra("Nom", extras2.getString("Nom"));
            }
            startActivity(intent1);
        }
    }
    protected void modifierInfoUtilisateur()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, modifierInfoUtilisateur.class);

        Intent intent2 = getIntent();
        if (intent2!= null)
        {
            Bundle extras2 = intent2.getExtras();
            if (extras2 != null)
            {
                intent1.putExtra("Nom", extras2.getString("Nom"));
            }
            startActivity(intent1);
        }
    }

    protected void modifierInfoDestinataire()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, modifierInfoDestinataire.class);

        Intent intent2 = getIntent();
        if (intent2!= null)
        {
            Bundle extras2 = intent2.getExtras();
            if (extras2 != null)
            {
                intent1.putExtra("Nom", extras2.getString("Nom"));
            }
            startActivity(intent1);
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonScanBarcode:
                startActivity(new Intent(this,ScannedBarcodeActivity.class));
                break;
            /*case R.id.buttonTakePicture:
                startActivity(new Intent(this,PictureBarcodeActivity.class));
                break;*/
        }
    }
}
