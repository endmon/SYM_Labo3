package ch.heigvd.scan;


import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

    static public final int  AUTHENTICATE_MAX = 10;
    static public final int  AUTHENTICATE_MEDIUM = 5;
    static public final int  AUTHENTICATE_LOW = 0;

    static private int authenticateLevel;

    private Button btnHighSecurity;
    private Button btnMediumSecurity;
    private Button btnLowSecurity;
    private NFCReader nfcReader;

    static public int getAuthenticateLevel() {
        return authenticateLevel;
    }

    static public void setAuthenticateLevel(int level){
        authenticateLevel = level;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);

        btnHighSecurity = findViewById(R.id.btnHighSecurity);
        btnMediumSecurity = findViewById(R.id.btnMediumSecurity);
        btnLowSecurity = findViewById(R.id.btnLowSecurity);

        nfcReader = new NFCReader(this);

        if(!nfcReader.checkNFC()){
            finish();
            return;
        }

        btnHighSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAuthenticateLevel(AUTHENTICATE_MAX);
                authenticateLevel--;
            }
        });

        btnMediumSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAuthenticateLevel(AUTHENTICATE_MEDIUM);
                authenticateLevel--;
            }
        });

        btnLowSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAuthenticateLevel(AUTHENTICATE_LOW);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nfcReader.onActivityNewIntent(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        nfcReader.onActivityResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcReader.onActivityPause();
    }

    private void checkAuthenticateLevel(int level) {
        if(authenticateLevel >= level) {
            Toast.makeText(ContentActivity.this, "niveau d'authentification suffisant", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(ContentActivity.this, "niveau d'authentification insuffisant", Toast.LENGTH_LONG).show();
        }
    }

}
