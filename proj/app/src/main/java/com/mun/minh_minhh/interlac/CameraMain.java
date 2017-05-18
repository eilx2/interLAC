package com.mun.minh_minhh.interlac;



import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Button;

import com.google.zxing.Result;
import com.mun.minh_minhh.interlac.Events.ArtworkViewMain;
import me.dm7.barcodescanner.core.CameraWrapper;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * Created by holenr on 13.05.2017
 */
public class CameraMain extends BasicActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private Camera mCamera;
    private CameraPreview mPreview;

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        // this device has a camera
// no camera on this device
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

//    /** A safe way to get an instance of the Camera object. */
//    public static Camera getCameraInstance(){
//        Camera c = null;
//        try {
//            c = Camera.open(); // attempt to get a Camera instance
//        }
//        catch (Exception e){
//            // Camera is not available (in use or does not exist)
//        }
//        return c; // returns null if camera is unavailable
//    }

    private void initScanButton() {
        final Button button = (Button) findViewById(R.id.testButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Server.writeReview(1,new Review("john","lol,shit",0,2));
                Intent intent = new Intent(CameraMain.this, ArtworkViewMain.class);
                intent.putExtra("id",1);
                startActivity(intent);

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_main);
        super.initBottomNavigation();

//        if (!permissionGranted())
//            getCameraPermission();

        //inflate navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        initScanButton();
    }

    public void onClick(View v) {
        mScannerView = new ZXingScannerView(this);

        setContentView(mScannerView);
        mScannerView.setResultHandler(this);

        mScannerView.startCamera();


    }



    //@Override
    //public void onStop() {
        //super.onStop();
        //mScannerView.stopCamera();}


    //@Override
    //protected void onPause() {
     //   super.onPause();
       // mScannerView.stopCamera();}
    @Override
    protected void onPause() {
        super.onPause();
       }

    //@Override
    //public void onResume() {
    //    super.onResume();
    //    mScannerView.startCamera();
    //}

    @Override
    protected void onStop() {
        super.onStop();
       // mScannerView.stopCameraPreview();
      //  mScannerView.stopCamera();


    }

    @Override
    public void handleResult(Result result) {
        //        // do whatever with result here!
        // this here sets a message popup to appear...
        Log.v("handleResult", result.getText());
        Intent intent = new Intent(CameraMain.this, ArtworkViewMain.class);
        intent.putExtra("id",result.getText());
        startActivity(intent);

        mScannerView.stopCameraPreview();
        mScannerView.stopCamera();
        //Resume scanning
        // Uncomment the following line to allow scanning to resume again!
      //  mScannerView.resumeCameraPreview(this);
    }







}




//public class CameraMain extends BasicActivity{
//
//    private Camera mCamera;
//    private CameraPreview mPreview;
//
//    /** Check if this device has a camera */
//    private boolean checkCameraHardware(Context context) {
//        // this device has a camera
//// no camera on this device
//        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
//    }
//
//    /** A safe way to get an instance of the Camera object. */
//    public static Camera getCameraInstance(){
//        Camera c = null;
//        try {
//            c = Camera.open(); // attempt to get a Camera instance
//        }
//        catch (Exception e){
//            // Camera is not available (in use or does not exist)
//        }
//        return c; // returns null if camera is unavailable
//    }
//
//    private void initScanButton() {
//        final Button button = (Button) findViewById(R.id.scan_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //Server.writeReview(1,new Review("john","lol,shit",0,2));
//                Intent intent = new Intent(CameraMain.this, ArtworkViewMain.class);
//                intent.putExtra("id",1);
//                startActivity(intent);
//
//            }
//        });
//    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera_main);
//        super.initBottomNavigation();
//
//
//        if (!permissionGranted())
//            getCameraPermission();
//
//        // Create an instance of Camera
//        mCamera = getCameraInstance();
//
//        // Create our Preview view and set it as the content of our activity.
//        mPreview = new CameraPreview(this, mCamera);
//        RelativeLayout preview = (RelativeLayout) findViewById(R.id.camera_preview);
//
//        //inflate navigation
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
//        BottomNavHelp.disableShiftMode(bottomNavigationView);
//        Menu menu = bottomNavigationView.getMenu();
//        MenuItem menuItem = menu.getItem(2);
//        menuItem.setChecked(true);
//
//        initScanButton();
//
//
//
//        preview.addView(mPreview,0);
//    }
//
//
//
//    private boolean permissionGranted() {
//        int permissionCode = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//        return (permissionCode == PackageManager.PERMISSION_GRANTED);
//    }
//
//    private void getCameraPermission() {
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mCamera.release();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
//}