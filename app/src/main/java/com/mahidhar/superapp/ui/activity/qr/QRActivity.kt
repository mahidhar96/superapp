package com.mahidhar.superapp.ui.activity.qr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.mahidhar.superapp.R
import com.mahidhar.superapp.ui.activity.WebAppActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class QRActivity : AppCompatActivity() {
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var analyzer: QRAnalyzer
    private lateinit var imageAnalysis: ImageAnalysis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        val previewView = findViewById<PreviewView>(R.id.previewView)

        Intent(applicationContext, WebAppActivity::class.java)
        analyzer = QRAnalyzer(applicationContext, this)
        cameraExecutor = Executors.newSingleThreadExecutor()
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider, previewView)
        }, ContextCompat.getMainExecutor(this))
    }

    private fun bindPreview(cameraProvider: ProcessCameraProvider, previewView: PreviewView) {
        val preview: Preview = Preview.Builder()
            .build()
        val cameraSelector: CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        preview.setSurfaceProvider(previewView.createSurfaceProvider(null))

        imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(cameraExecutor, analyzer)

        cameraProvider.bindToLifecycle(
            this as LifecycleOwner,
            cameraSelector,
            imageAnalysis,
            preview
        )
    }

    fun clearAnalyzer() {
        imageAnalysis.clearAnalyzer()
        Log.i("QRActivity", "Analyzer cleared")
    }

    override fun onResume() {
        super.onResume()
        if (this::imageAnalysis.isInitialized) {
            imageAnalysis.setAnalyzer(cameraExecutor, analyzer)
            Log.i("QRActivity onResume", "Analyser set")
        }
        Log.i("QRActivity onResume", "Activity Resumed")
    }
}