package com.mopanesystems.zipos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mopanesystems.zipos.presentation.common.navigation.MainScreen
import com.mopanesystems.zipos.presentation.common.theme.ZiPosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZiPosTheme {
                MainScreen()
            }
        }
    }
}
