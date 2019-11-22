package ch.heigvd.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NFCActivity extends AppCompatActivity {

    private Button btnConnect = null;
    private EditText editUsername = null;
    private EditText editPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nfc);
        btnConnect = findViewById(R.id.btnConnect);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( NFCActivity.this, ContentActivity.class);
                NFCActivity.this.startActivity(intent);

            }
        });
    }
}
