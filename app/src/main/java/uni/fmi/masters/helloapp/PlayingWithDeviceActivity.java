package uni.fmi.masters.helloapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayingWithDeviceActivity extends AppCompatActivity {

    public static final int IMAGE_CAPTURE = 666;
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
    private View.OnLongClickListener onLongClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {

            if(ContextCompat.checkSelfPermission(PlayingWithDeviceActivity.this
                    , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(PlayingWithDeviceActivity.this,
                        new String[] { Manifest.permission.CAMERA}, IMAGE_CAPTURE);

            }else{
                takePicture();
            }

            return false;
        }
    };
    private View.OnClickListener onShortClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pictureIV.setImageBitmap(null);
            Toast.makeText(PlayingWithDeviceActivity.this, "Stuffs are happening",
                    Toast.LENGTH_SHORT).show();
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

        pictureIV.setOnLongClickListener(onLongClick);
        pictureIV.setOnClickListener(onShortClick);
    }


    private void takePicture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(takePictureIntent, IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap picture = (Bitmap) extras.get("data");

            pictureIV.setImageBitmap(picture);
        } else {
            Toast.makeText(this, "No image taken", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == IMAGE_CAPTURE){
            if(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePicture();
            }else{
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}