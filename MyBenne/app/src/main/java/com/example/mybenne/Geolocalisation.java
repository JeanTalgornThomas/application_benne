package com.example.mybenne;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Geolocalisation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button BetaMairie;
    Button retour;

    DatabaseManager db = new DatabaseManager(this);
    LocationManager locationManager;

    SharedPreferences permissionStatus;
    private boolean sentToSettings = false;
    private static final int SMS_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalisation);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        retour = findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retour();
            }
        });

        BetaMairie = findViewById(R.id.BetamoveBenne);
        BetaMairie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 *  NUM DESTINATAIRE
                 */
                Cursor colStrArr1 = db.getDataNom("destinataire");
                colStrArr1.moveToFirst();
                Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(colStrArr1));
                String destnumber = colStrArr1.getString(2);
                Toast.makeText(Geolocalisation.this, destnumber , Toast.LENGTH_LONG).show();


                /**
                 *  INFO BENNE
                 */
                String qrcode, latitude, longitude;

                /*Cursor colStrArr3 = db.getDataBenne();
                colStrArr3.moveToFirst();
                Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(colStrArr3));
                qrcode = colStrArr3.getString(0);
                latitude = colStrArr3.getString(1);
                longitude = colStrArr3.getString(2);*/

                qrcode = "1";
                latitude = "49.847177";
                longitude = "3.287543";

                /**
                 *  INFO UTILISATEUR
                 */
                Cursor colStrArr2 = db.getDataNom("utilisateur");
                colStrArr2.moveToFirst();
                Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(colStrArr2));
                String userinfo = "utilisateur :" +
                        colStrArr2.getString(0) +
                        colStrArr2.getString(1) +
                        colStrArr2.getString(2);

                String message = "Benne " + qrcode + " aux coordonnées " + latitude + ", " + longitude + "mise à jour par : " + userinfo;
                SendMessage(destnumber, message);


                if (ActivityCompat.checkSelfPermission(Geolocalisation.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Geolocalisation.this, Manifest.permission.SEND_SMS)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Geolocalisation.this);
                        builder.setTitle("Need SMS Permission");
                        builder.setMessage("This app needs SMS permission to send Messages.");
                        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                ActivityCompat.requestPermissions(Geolocalisation.this,
                                        new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CONSTANT);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    } else {
                        ActivityCompat.requestPermissions(Geolocalisation.this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CONSTANT);
                    }
                }
            }
        });
    }
    public void SendMessage(String strMobileNo, String strMessage) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(strMobileNo, null, strMessage, null, null);
            Toast.makeText(getApplicationContext(), "Your Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(Geolocalisation.this, "if you agreed permissions , repress the send button" , Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Benne par défaut
        LatLng BenneMairie = new LatLng(49.847177, 3.287543);
        LatLng BenneHotelMemorial = new LatLng(49.846054, 3.286342);
        LatLng BenneGoldenPub = new LatLng(49.847342, 3.287045);
        LatLng BenneBoulanderiePaul = new LatLng(49.845640, 3.290009);
        LatLng BenneEcoleLyon = new LatLng(49.845280, 3.284628);
        mMap.addMarker(new MarkerOptions().position(BenneMairie).title("Benne de la Mairie"));
        mMap.addMarker(new MarkerOptions().position(BenneHotelMemorial).title("Benne de l'Hotel Memorial"));
        mMap.addMarker(new MarkerOptions().position(BenneGoldenPub).title("Benne du Golden Pub"));
        mMap.addMarker(new MarkerOptions().position(BenneBoulanderiePaul).title("Benne de la boulangerie Paul"));
        mMap.addMarker(new MarkerOptions().position(BenneEcoleLyon).title("Benne de l'Ecole Lyon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BenneMairie));
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