package ch.heigvd.scan;


import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.sql.Timestamp;

public class ContentActivity extends NFCReader {

    static public final int  AUTHENTICATE_MAX = 10;
    static public final int  AUTHENTICATE_MEDIUM = 30;
    static public final int  AUTHENTICATE_LOW = 60;

    static private long lastSeen;

    private Button btnHighSecurity;
    private Button btnMediumSecurity;
    private Button btnLowSecurity;

    static public void setLastSeen(){
        lastSeen = new Date().getTime();
    }

    static public boolean hasRecentAuthenticate(int level) {
        return new Timestamp(new Date().getTime()).before( new Timestamp(lastSeen + level * 1000));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);

        btnHighSecurity = findViewById(R.id.btnHighSecurity);
        btnMediumSecurity = findViewById(R.id.btnMediumSecurity);
        btnLowSecurity = findViewById(R.id.btnLowSecurity);

        btnHighSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAuthenticate(AUTHENTICATE_MAX);
            }
        });

        btnMediumSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAuthenticate(AUTHENTICATE_MEDIUM);
            }
        });

        btnLowSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAuthenticate(AUTHENTICATE_LOW);
            }
        });
    }

    private void checkAuthenticate( int level){
        if(hasRecentAuthenticate( level)) {
            Toast.makeText(ContentActivity.this, "niveau d'authentification suffisant", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(ContentActivity.this, "niveau d'authentification insuffisant", Toast.LENGTH_LONG).show();
        }
    }

}
