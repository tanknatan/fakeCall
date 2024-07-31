package com.example.fakecall.presentation.tg.call

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fakecall.R
import com.example.fakecall.presentation.navigateTo
import com.example.fakecall.presentation.tg.TelegramDetailActivity
import com.example.fakecall.ui.theme.FakeCallTheme

class TelegramCallActivity: ComponentActivity(){
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeCallTheme {
                TelegramCallScreen(onAccept = {navigateTo(TelegramCallAcceptActivity::class.java)})
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
    fun TelegramCallScreen(onAccept: () -> Unit) {

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
            Image(
                painter = painterResource(id = R.drawable.back_ground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.scaryteachertelegramcall),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.calltelegram),
                    contentDescription = null
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = {onChangedSound()}) {
                    Image(
                        painter = painterResource(id = R.drawable.calltelegramaudio),
                        contentDescription = null
                    )
                }

                TextButton(onClick = {onChangedSound()}) {
                    Image(
                        painter = painterResource(id = R.drawable.calltelegrammicro),
                        contentDescription = null
                    )
                }

                TextButton(onClick = onAccept) {
                    Image(
                        painter = painterResource(id = R.drawable.calltelegramaccept),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

