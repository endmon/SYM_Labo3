package ch.heigvd.scan;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NFCActivity extends AppCompatActivity {

    final private String USERNAME = "Bob";
    final private String PASSWORD = "Password";

    private Button btnConnect;
    private EditText editUsername;
    private EditText editPassword ;
    private NFCReader nfcReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nfc);
        btnConnect = findViewById(R.id.btnConnect);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

        nfcReader = new NFCReader(this);

        if (! nfcReader.checkNFC()) {
            finish();
            return;
        }


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( editUsername.getText().toString().equals( USERNAME) &&
                    editPassword.getText().toString().equals( PASSWORD) &&
                    ContentActivity.getAuthenticateLevel() == ContentActivity.AUTHENTICATE_MAX
                ){
                    Intent intent = new Intent( NFCActivity.this, ContentActivity.class);
                    NFCActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(NFCActivity.this, "mot de passe ou identifiant erroné ou niveau de sécurité insuffisant" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "new intent" , Toast.LENGTH_LONG).show();
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

}
