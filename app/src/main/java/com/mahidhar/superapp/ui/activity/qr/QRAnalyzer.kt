package com.mahidhar.superapp.ui.activity.qr

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.fragment.app.FragmentManager
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.mahidhar.superapp.ui.activity.payment.PaymentActivity
import kotlin.system.exitProcess

class QRAnalyzer(
    private val context: Context,
    val activity: QRActivity
) : ImageAnalysis.Analyzer {

    override fun analyze(imageProxy: ImageProxy) {
        scanBarcode(imageProxy)
    }

    @SuppressLint("UnsafeExperimentalUsageError")
    private fun scanBarcode(imageProxy: ImageProxy) {
        imageProxy.image?.let { image ->
            val inputImage = InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient()
            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    imageProxy.close()
                    activity.clearAnalyzer()
                    readBarcodeData(barcodes)
                }
                .addOnFailureListener {
                    Log.i("NOQR", "No QR Code Detected")
                    imageProxy.close()
                }
//                .addOnCompleteListener {
//                    imageProxy.close()
//                    if (it.isSuccessful) {
//                        readBarcodeData(it.result as List<Barcode>)
//                        imageAnalyzer.clearAnalyzer()
//                    } else {
//                        it.exception?.printStackTrace()
//                    }
//                }
        }
    }

    private fun readBarcodeData(barcodes: List<Barcode>) {
        if (barcodes.size > 0) {
            Log.i("QRCODE RAW VALUE", barcodes[0].rawValue.toString())
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra("raw", barcodes[0].rawValue.toString())
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
        }
//        for (barcode in barcodes) {
//            when (barcode.valueType) {
//                Barcode.TYPE_TEXT ->
//                Barcode.TYPE_URL -> {
//                    if (!bottomSheet.isAdded)
//                        bottomSheet.show(fragmentManager, "")
//                    bottomSheet.updateURL(barcode.url?.url.toString())
//                }
//            }
//        }
    }
}