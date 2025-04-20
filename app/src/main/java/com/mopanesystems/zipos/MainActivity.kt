package com.mopanesystems.zipos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mopanesystems.zipos.navigation.AppNavGraph
import com.mopanesystems.zipos.ui.theme.ZiPosTheme
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
