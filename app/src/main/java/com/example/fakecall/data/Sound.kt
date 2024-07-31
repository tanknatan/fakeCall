package com.example.fakecall.data

import androidx.annotation.RawRes
import com.example.fakecall.R

enum class Sound(
    @RawRes val rawRes: Int,

)
{
    Iphone(R.raw.iphone),
    whatsapp(R.raw.iphone),
    sound(R.raw.iphone);
    companion object{
        fun getSound(rawRes: Int) = values().find { it.rawRes == rawRes }
    }

}