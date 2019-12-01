package ch.heigvd.scan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class QRCodeActivity extends AppCompatActivity {

    private Button button;
    static TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        button = findViewById(R.id.btnScan);
        textViewResult = findViewById(R.id.textResult);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
                Intent intent = new Intent(QRCodeActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });
    }


}
