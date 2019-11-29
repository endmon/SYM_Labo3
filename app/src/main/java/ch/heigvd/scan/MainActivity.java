/*
 * File         : MainActivity.java
 * Project      : Labo3 SYM
 * Author       : Bouyiatiotis Stéphane - Da Costa Gomez - Lopes Gouveia Miguel Angelo
 * Modifié le   : 29.11.2019
 * Description  : Contient la classe contrôlant l'activité principale qui contient 2 boutons)
 *
 */

package ch.heigvd.scan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button nfc;
    private Button qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        nfc = findViewById(R.id.btnNFC);
        qrcode = findViewById(R.id.btnQRCODE);

        nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NFCActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRCodeActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
