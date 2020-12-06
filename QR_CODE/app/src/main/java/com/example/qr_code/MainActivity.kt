package com.example.qr_code

import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val MY_PERMISSIONS_REQUEST_CAMERA = 123
        val barcodeDetector: BarcodeDetector
        val cameraSource: CameraSource
        val cameraView: SurfaceView
        val qrResult: TextView

        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT > 22) {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA))
                    Toast.makeText(applicationContext, "Esta aplicación necesita acceder a la cámara para funcionar", Toast.LENGTH_SHORT).show()
                requestPermissions(String[]{ android.Manifest.permission.CAMERA }, MY_PERMISSIONS_REQUEST_CAMERA)
            }
        }
        //Texto con el resultado del qr
        //Texto con el resultado del qr
        qrResult = findViewById(R.id.resultado_qr) as TextView
        //Vista de la cámara
        //Vista de la cámara
        cameraView = findViewById(R.id.camera_view) as SurfaceView

        //Creama el lector de qr

        //Creama el lector de qr
        barcodeDetector = BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()

        //Creama la camara

        //Creama la camara
        cameraSource = CameraSource.Builder(this, barcodeDetector)
                .build()

        // Prepara el lector de qr

        // Prepara el lector de qr
        cameraView.getHolder().addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

                //Verifica si el usuario ha dado permiso para la camara
                if (ContextCompat.checkSelfPermission(baseContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        cameraSource.start(cameraView.getHolder())
                    } catch (ie: IOException) {
                        Log.e("CAMERA SOURCE", ie.getMessage())
                    }
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })

        //Establece la función al escanear un código

        //Establece la función al escanear un código
        barcodeDetector.setProcessor(object : Detector.Processor<Any?> {
            override fun release() {}
            fun receiveDetections(detections: Detections<*>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() != 0) {
//Establece el valor del qr en el textview
                    qrResult.post(Runnable { qrResult.setText(barcodes.valueAt(0).displayValue.toString()) })
                    //Cierra el detector de códigos
                    barcodeDetector.release()
                }
            }
        })

    }
}