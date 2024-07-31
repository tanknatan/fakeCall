package com.example.fakecall.presentation.tg.video


import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.fakecall.R
import com.example.fakecall.presentation.navigateTo
import com.example.fakecall.ui.theme.FakeCallTheme
import com.example.fakecall.ui.theme.telegram
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TelegramVideoCallActivity : ComponentActivity() {


    private lateinit var cameraExecutor: ExecutorService
    private lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>
    private var mediaPlayer: MediaPlayer? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cameraPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your app.
                startCamera()
            } else {
                // Permission is denied. Handle accordingly.
            }
        }

        // Check if permission is already granted
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            // Request the camera permission
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startCamera() {
        setContent {
            FakeCallTheme {
                TelegramVideoCallScreen(onAccept = {
                    navigateTo(TelegramVideoCallAcceptActivity::class.java)
                })
            }
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }
    @Composable
    fun TelegramVideoCallScreen(onAccept: () -> Unit) {

        val context = LocalContext.current
        val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
        val previewView = remember { PreviewView(context) }

        DisposableEffect(cameraProviderFuture) {
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    context as ComponentActivity,
                    cameraSelector,
                    preview
                )
            } catch (exc: Exception) {
                Log.e("CameraPreviewScreen", "Use case binding failed", exc)
            }

            onDispose {
                cameraProvider.unbindAll()
            }
        }

        fun onChangedSound() {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            } else {
                mediaPlayer?.start()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(telegram),


            ) {
            AndroidView(
                factory = { previewView },
                modifier = Modifier.fillMaxSize()
            )
            Column {
                Spacer(modifier = Modifier.height(150.dp))
                Image(
                    painter = painterResource(id = R.drawable.roundteacher),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()


                )
                Spacer(modifier = Modifier.height(5.dp))
                Image(
                    painter = painterResource(id = R.drawable.calltelegramteacher),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                Spacer(modifier = Modifier.height(5.dp))


            }




            Row(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 0.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { onChangedSound() }) {
                    Image(
                        painter = painterResource(id = R.drawable.calltelegramaudio),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)

                    )
                }
                TextButton(onClick = onAccept) {
                    Image(
                        painter = painterResource(id = R.drawable.videocalltelegramcamera),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }

                TextButton(onClick = { onChangedSound() }) {
                    Image(
                        painter = painterResource(id = R.drawable.videocalltelegrammicro),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }

                TextButton(onClick = onAccept) {
                    Image(
                        painter = painterResource(id = R.drawable.videocalltelegramaccept),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        mediaPlayer = MediaPlayer.create(this, R.raw.iphone)
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}


