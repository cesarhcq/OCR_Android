package engenoid.tessocrtest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import org.opencv.android.OpenCVLoader;

import engenoid.tessocrtest.Core.CameraEngine;
import engenoid.tessocrtest.Core.ExtraViews.FocusBoxView;
import engenoid.tessocrtest.Core.Imaging.Tools;
import engenoid.tessocrtest.Core.TessTool.TessAsyncEngine;


public class FotoFrameWork extends Activity implements SurfaceHolder.Callback, View.OnClickListener,
        Camera.PictureCallback, Camera.ShutterCallback {


    static {
        System.loadLibrary("ndktest");
    }

    static final String TAG = "DBG_" + FotoFrameWork.class.getName();

    static {
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCV not loaded");
        } else {
            Log.d(TAG, "OpenCV loaded");
        }
    }

    public static Bitmap bmp;
    public static Bitmap bmp2;

    Button shutterButton;
    Button focusButton;
    FocusBoxView focusBox;
    SurfaceView cameraFrame;
    CameraEngine cameraEngine;

    private Button Enviar;
    private Button Recomecar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tess);

        Recomecar = (Button) findViewById(R.id.btnRecomecar);
        Enviar = (Button) findViewById(R.id.btnEnviar);

        Recomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cameraEngine!=null && cameraEngine.isOn()){
                    cameraEngine.stop();
                    cameraEngine.start();
                }

            }
        });

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jose.lavaquial@hubz.com.br"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Projeto hubz de Desenvolvimento Tecnológico");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Medição Eletrônica - hubz191\n\n" + "Medição Consumo de Energia por Visualização eletrônica\n" +
                        "\n"+"Cliente: " + TessAsyncEngine.ocrresult2 + "\n" + "Eletricidade: "+TessAsyncEngine.ocrresult + "\n\n\n" + "www.hubz.com.br" + "\n" + "hubz@hubz.com.br" + "\n" + "+55 21 2279 7002");
                emailIntent.setType("message/rfc822");
                startActivity(emailIntent);
            }
        });

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Log.d(TAG, "Surface Created - starting camera");

        if (cameraEngine != null && !cameraEngine.isOn()) {
            cameraEngine.start();
        }

        if (cameraEngine != null && cameraEngine.isOn()) {
            Log.d(TAG, "Camera engine already on");
            return;
        }

        cameraEngine = CameraEngine.New(holder);
        cameraEngine.start();

        Log.d(TAG, "Camera engine started");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        cameraFrame = (SurfaceView) findViewById(R.id.camera_frame);
        shutterButton = (Button) findViewById(R.id.shutter_button);
        focusBox = (FocusBoxView) findViewById(R.id.focus_box);
        focusButton = (Button) findViewById(R.id.focus_button);

        shutterButton.setOnClickListener(this);
        focusButton.setOnClickListener(this);

        SurfaceHolder surfaceHolder = cameraFrame.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        cameraFrame.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (cameraEngine != null && cameraEngine.isOn()) {
            cameraEngine.stop();
        }

        SurfaceHolder surfaceHolder = cameraFrame.getHolder();
        surfaceHolder.removeCallback(this);
    }

    @Override
    public void onClick(View v) {
        if(v == shutterButton){
            if(cameraEngine != null && cameraEngine.isOn()){
                cameraEngine.takePicture(this, this, this);
            }
        }

        if(v == focusButton){
            if(cameraEngine!=null && cameraEngine.isOn()){
                cameraEngine.requestFocus();
            }
        }

    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {


        Log.d(TAG, "Picture taken");

        if (data == null) {
            Log.d(TAG, "Got null data");
            return;
        }

        bmp = Tools.getFocusedBitmap(this, camera, data, focusBox.getBox());
        bmp2 = Tools.getFocusedBitmap(this, camera, data, focusBox.getBox2());


        Log.d(TAG, "Got bitmap");

        Log.d(TAG, "Initialization of TessBaseApi");

        new TessAsyncEngine().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, this, bmp);

    }

    @Override
    public void onShutter() {

    }
    public native String callNative();
}