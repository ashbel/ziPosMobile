package com.mopanesystems.zipos.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mopanesystems.zipos.presentation.auth.viewmodel.AuthViewModel

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
