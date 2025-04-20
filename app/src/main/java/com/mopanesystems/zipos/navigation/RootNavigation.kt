package com.mopanesystems.zipos.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mopanesystems.zipos.MainScreen
import com.mopanesystems.zipos.ui.screens.login.AuthViewModel
import com.mopanesystems.zipos.ui.screens.login.LoginScreen

@Composable
fun RootNavigation(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val isUserAuthenticated = authViewModel.authState != null

    when (isUserAuthenticated) {

        true -> {
            MainScreen() // Shows AppNavGraph + Bottom Nav
        }

        false -> {

        }
    }
}
