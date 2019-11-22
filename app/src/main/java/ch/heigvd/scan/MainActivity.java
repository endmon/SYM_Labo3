package ch.heigvd.scan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button nfc     = null;
    private Button qrcode  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        nfc = findViewById(R.id.btnNFC);
        qrcode = findViewById(R.id.btnQRCODE);

        nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NFC.class);
                MainActivity.this.startActivity(intent);

            }
        });

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRCode.class);
                MainActivity.this.startActivity(intent);
            }
        });




    }
}
