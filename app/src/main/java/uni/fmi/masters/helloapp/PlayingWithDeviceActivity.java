package uni.fmi.masters.helloapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayingWithDeviceActivity extends AppCompatActivity {

    ImageView pictureIV;
    Button turnB;
    TextView counterImage;
    TextView counterFlash;

    CameraManager cameraManager;
    String cameraId;

    boolean isFlashOn;
    private int flashCount;

    private View.OnClickListener onFlashClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try{
                cameraManager.registerTorchCallback(new CameraManager.TorchCallback() {
                    @Override
                    public void onTorchModeChanged(@NonNull String cameraId, boolean enabled) {
                        super.onTorchModeChanged(cameraId, enabled);
                        flashCount++;
                        counterFlash.setText("Flash Count: " + flashCount);

                        if(isFlashOn){
                            turnB.setText("Turn Off");
                        }else {
                            turnB.setText("Turn On");
                        }
                    }
                }, null);

                isFlashOn = !isFlashOn;
                cameraManager.setTorchMode(cameraId, isFlashOn);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_with_device);

        pictureIV = findViewById(R.id.pictureIV);
        turnB = findViewById(R.id.turnB);
        counterFlash = findViewById(R.id.flashCounterTV);
        counterImage = findViewById(R.id.pictureCounterTV);

        turnB.setOnClickListener(onFlashClick);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try{
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            Toast.makeText(this, "Не мога да достъпя камерата", Toast.LENGTH_SHORT).show();
        }
    }


}