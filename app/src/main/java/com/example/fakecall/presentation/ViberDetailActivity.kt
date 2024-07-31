package com.example.fakecall.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fakecall.R
import com.example.fakecall.ui.theme.FakeCallTheme

class ViberDetailActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeCallTheme {
                ViberDetailScreen(onMenuClick = {navigateTo(MainActivity::class.java)})
            }
        }
    }
}

@Composable
fun ViberDetailScreen( onMenuClick : () -> Unit = {}, onCalling : () -> Unit = {}, onVideoCalling : () -> Unit = {}) {
    Image(
        painter = painterResource(id = R.drawable.back_ground),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = onMenuClick,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
        Image(painter = painterResource(id = R.drawable.scary_teacher), contentDescription = null)

        Spacer(modifier = Modifier.height(50.dp))

        TextButton(
            onClick = onCalling,
            modifier = Modifier

        ) {
            Image(
                painter = painterResource(id = R.drawable.calling),
                contentDescription = "Farm Icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            )


        }
        Spacer(modifier = Modifier.height(0.dp))
        TextButton(
            onClick = onVideoCalling,
            modifier = Modifier

        ) {
            Image(
                painter = painterResource(id = R.drawable.videocall),
                contentDescription = "Farm Icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            )


        }


    }

}