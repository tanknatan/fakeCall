package com.example.fakecall.presentation


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fakecall.R
import com.example.fakecall.ui.theme.FakeCallTheme
import com.example.fakecall.ui.theme.Purple40
import com.example.fakecall.ui.theme.Purple80
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeCallTheme {
                SplashScreen (viewModel(), { navigateToMain() })
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

class SplashScreenViewModel : ViewModel() {
    private val _progress = MutableLiveData(0f)
    val progress: LiveData<Float> = _progress

    init {
        startLoading()
    }

    private fun startLoading() {
        viewModelScope.launch {
            while ((_progress.value ?: 0f) < 1f) {
                delay(25)
                _progress.value = (_progress.value ?: 0f) + 0.01f
            }
        }
    }
}
@Composable
fun SplashScreen(viewModel: SplashScreenViewModel, onLoadingComplete: () -> Unit) {
    val progress by viewModel.progress.observeAsState(0f)

    if (progress >= 1f) {
        onLoadingComplete()
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
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = progress,
                color = Purple80,
                trackColor = Purple40,
                modifier = Modifier
                    .fillMaxWidth( 0.8f)
                    .height(24.dp)
                    .clip(
                        shape = RoundedCornerShape(50)
                    )
            )


        }
    }
}



