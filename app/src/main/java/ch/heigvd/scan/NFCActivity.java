/*
 * File         : NFCActivity.java
 * Project      : Labo3 SYM
 * Author       : Bouyiatiotis Stéphane - Da Costa Gomez - Lopes Gouveia Miguel Angelo
 * Modifié le   : 29.11.2019
 * Description  : Contient la classe contrôlant l'activité de connexion au contenu sécurisé.
 *                L'activité hérite de NFCReader et  contient un champs texte, un champs mot de passe et un bouton.
 *
 */

package ch.heigvd.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NFCActivity extends NFCReader {

    final private String USERNAME = "Bob";
    final private String PASSWORD = "Password";

    private Button btnConnect;
    private EditText editUsername;
    private EditText editPassword ;

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
                if( editUsername.getText().toString().equals( USERNAME) &&
                    editPassword.getText().toString().equals( PASSWORD) &&
                    ContentActivity.hasRecentAuthenticate(ContentActivity.AUTHENTICATE_MAX)
                ){
                    Intent intent = new Intent( NFCActivity.this, ContentActivity.class);
                    NFCActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(NFCActivity.this, "mot de passe ou identifiant erroné ou niveau de sécurité insuffisant" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
