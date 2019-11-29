/*
 * File         : ContentActivity.java
 * Project      : Labo3 SYM
 * Author       : Bouyiatiotis Stéphane - Da Costa Gomez - Lopes Gouveia Miguel Angelo
 * Modifié le   : 29.11.2019
 * Description  : Contient la classe contrôlant l'activité du contenu sécurisé.
 *                L'activité hérite de NFCReader et contient un champs texte, un champs mot de passe et un bouton.
 *
 */

package ch.heigvd.scan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    /**
     * @breif   : permet de mettre à jour la dernière lecture en dehors de l'activité
     */
    static public void setLastSeen(){
        lastSeen = new Date().getTime();
    }

    /**
     * @brief       : vérifie si l'authentification est assez récente pour le niveau en paramètre
     * @param level : int, le niveau de sécurité
     * @return boolean, vrai si l'authentification est assez récente pour le niveau cible
     */
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

    /**
     * @brief       : affiche un Toast selon si l'authentification est assez récente pour le niveau en paramètre
     * @param level : int, le niveau de sécurité
     */
    private void checkAuthenticate( int level){
        if(hasRecentAuthenticate( level)) {
            Toast.makeText(ContentActivity.this, "niveau d'authentification suffisant", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(ContentActivity.this, "niveau d'authentification insuffisant", Toast.LENGTH_LONG).show();
        }
    }

}
