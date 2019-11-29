package ch.heigvd.scan;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

abstract public class NFCReader extends AppCompatActivity {
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null){
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if(!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC is disabled.", Toast.LENGTH_LONG).show();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            String type = intent.getType();
            if ("text/plain".equals(type)) {
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                if(tag.toString().equals("TAG: Tech [android.nfc.tech.NfcA, android.nfc.tech.Ndef]")){
                    ContentActivity.setLastSeen();
                    Toast.makeText(this, "le niveau de sécurité a été mis à jour", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Wrong mime type: " + type, Toast.LENGTH_LONG).show();

            }
        }

    }

    protected void onResume(){
        super.onResume();

        if(nfcAdapter == null)
            return;

        final Intent intent = new Intent(this.getApplicationContext(),
                this.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent =
                PendingIntent.getActivity(this.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType("text/plain");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            Toast.makeText(this, "MalformedMimeTypeException", Toast.LENGTH_LONG).show();
        }

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, filters, techList);
    }

    protected void onPause() {
        super.onPause();

        if(nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(this);
    }

}
