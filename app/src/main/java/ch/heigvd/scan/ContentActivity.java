package ch.heigvd.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

    private final int  AUTHENTICATE_MAX = 10;
    private final int  AUTHENTICATE_MEDIUM = 5;
    private final int  AUTHENTICATE_LOW = 0;

    private int timeout = AUTHENTICATE_MAX;

    private Button btnHighSecurity     = null;
    private Button btnMediumSecurity  = null;
    private Button btnLowSecurity  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);


        btnHighSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMediumSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnLowSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
