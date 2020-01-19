package com.example.mybenne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class modifierInfoUtilisateur extends AppCompatActivity {
    DatabaseManager db;

    EditText nom, prenom, telephone;
    Button retour;
    Button enregistrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_info_utilisateur);

        db = new DatabaseManager(this);

        nom=(EditText) findViewById(R.id.nom);
        prenom=(EditText) findViewById(R.id.prenom);
        telephone=(EditText) findViewById(R.id.telephone);

        retour = findViewById(R.id.retour);
        enregistrer = findViewById(R.id.enregistrer);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retour();
            }
        });

        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=db.insertData(nom.getText().toString(), prenom.getText().toString(), telephone.getText().toString(), "utilisateur");
                if(isInserted==true)
                    Toast.makeText(modifierInfoUtilisateur.this, "Données enregistrée !", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(modifierInfoUtilisateur.this, "Données non enregistrée !", Toast.LENGTH_LONG).show();
            }

        });
    }

    protected void retour()
    {
        Intent intent1 = new Intent();
        intent1.setClass(this, MainActivity.class);

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
}
