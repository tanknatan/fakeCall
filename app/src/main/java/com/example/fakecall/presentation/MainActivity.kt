package com.example.fakecall.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fakecall.ui.theme.FakeCallTheme
import com.example.fakecall.R
import com.example.fakecall.presentation.tg.TelegramDetailActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeCallTheme {
                MainScreen(
                    onTelegramClick = { navigateTo(TelegramDetailActivity::class.java) },
                    onWhatsappClick = { navigateTo(WhatsappDetailActivity::class.java) },
                    onViberClick = { navigateTo(ViberDetailActivity::class.java) }
                )


            }
        }
    }
}





fun Context.navigateTo(activityClass: Class<out ComponentActivity>) {
    startActivity(Intent(this, activityClass).apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    })
}

@Composable
fun MainScreen(
    onTelegramClick: () -> Unit = {},

    onWhatsappClick: () -> Unit = {},
    onViberClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    onMoreAppsClick: () -> Unit = {},
    onWallpaperClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.back_ground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(

            ) {
                Image(
                    painter = painterResource(id = R.drawable.scary_teacher),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                )

            }


            TextButton(onClick = onTelegramClick) {
                Image(
                    painter = painterResource(id = R.drawable.telepram),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                )

            }
            TextButton(onClick = onWhatsappClick) {
                Image(
                    painter = painterResource(id = R.drawable.whatsapp),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                )

            }
            TextButton(onClick = onViberClick) {
                Image(
                    painter = painterResource(id = R.drawable.viber),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                )

            }
            TextButton(onClick = onChatClick) {
                Image(
                    painter = painterResource(id = R.drawable.chat),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                )

            }
            TextButton(onClick = onMoreAppsClick) {
                Image(
                    painter = painterResource(id = R.drawable.moreapps),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                )

            }
            TextButton(onClick = onWallpaperClick) {
                Image(
                    painter = painterResource(id = R.drawable.wallpaper),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                )

            }
        }
    }

}

