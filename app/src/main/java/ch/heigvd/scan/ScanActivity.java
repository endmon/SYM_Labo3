package ch.heigvd.scan;

import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.Result;
import android.os.Bundle;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


//Code based on https://demonuts.com/scan-barcode-qrcodes/
public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result resultScan) {
        QRCodeActivity.textViewResult.setText(resultScan.getText());
        onBackPressed();

    }
}
