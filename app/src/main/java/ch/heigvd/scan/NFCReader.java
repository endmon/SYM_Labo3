package ch.heigvd.scan;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.widget.Toast;

public class NFCReader {
    private NfcAdapter nfcAdapter;
    private Activity activity;

    public NFCReader(Activity activity) {
        this.nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        this.activity = activity;
    }

    public boolean checkNFC(){
        if(nfcAdapter == null){
            // Stop here, we definitely need NFC
            Toast.makeText(activity, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!nfcAdapter.isEnabled()) {
            Toast.makeText(activity, "NFC is disabled.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public void onActivityNewIntent(Intent intent) {

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            String type = intent.getType();
            if ("text/plain".equals(type)) {
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                if(tag.toString().equals("TAG: Tech [android.nfc.tech.NfcA, android.nfc.tech.Ndef]")){
                    ContentActivity.setAuthenticateLevel(ContentActivity.AUTHENTICATE_MAX);
                }
            } else {
                Toast.makeText(activity, "Wrong mime type: " + type, Toast.LENGTH_LONG).show();

            }
        }

    }

    public void onActivityResume(){
        if(nfcAdapter == null)
            return;

        final Intent intent = new Intent(activity.getApplicationContext(),
                this.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent =
                PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType("text/plain");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            Toast.makeText(activity, "MalformedMimeTypeException", Toast.LENGTH_LONG).show();
        }

        nfcAdapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }

    public void onActivityPause() {
        if(nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(activity);
    }

}
