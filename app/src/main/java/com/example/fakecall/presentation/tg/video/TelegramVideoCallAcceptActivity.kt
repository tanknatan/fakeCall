package com.example.fakecall.presentation.tg.video

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fakecall.R
import com.example.fakecall.presentation.MainActivity
import com.example.fakecall.presentation.navigateTo
import com.example.fakecall.ui.theme.FakeCallTheme
import com.example.fakecall.ui.theme.telegram
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TelegramVideoCallAcceptActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeCallTheme {
                TelegramVideoCallAcceptScreen(onDrop = { navigateTo(MainActivity::class.java) })
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


    @Composable
    fun TelegramVideoCallAcceptScreen(onDrop: () -> Unit) {

        var time by remember { mutableStateOf<Long>(0L) }


        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            coroutineScope.launch {
                while (true) {
                    delay(1000L)
                    time++
                }
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
        ) {
            Column {

            }
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = formatTime(time),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red,
                        modifier = Modifier
                            .padding(0.dp)


                    )
                }
            }


            Row(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 0.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = {onChangedSound()}) {
                    Image(
                        painter = painterResource(id = R.drawable.calltelegramaudio),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)

                    )
                }
                TextButton(onClick = onDrop) {
                    Image(
                        painter = painterResource(id = R.drawable.videocalltelegramcamera),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }

                TextButton(onClick =  {onChangedSound()}) {
                    Image(
                        painter = painterResource(id = R.drawable.videocalltelegrammicro),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }

                TextButton(onClick = onDrop) {
                    Image(
                        painter = painterResource(id = R.drawable.videocalltelegramaccept),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
        }
    }


    fun formatTime(time: Long): String {
        val seconds = time % 60
        val minutes = (time / 60) % 60
        val hours = time / 3600
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

}

