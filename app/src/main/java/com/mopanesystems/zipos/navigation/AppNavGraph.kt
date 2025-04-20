package com.mopanesystems.zipos.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mopanesystems.zipos.ui.screens.dashboard.DashboardScreen
import com.mopanesystems.zipos.ui.screens.login.AuthViewModel
import com.mopanesystems.zipos.ui.screens.login.LoginScreen
import com.mopanesystems.zipos.ui.screens.products.EditProductScreen
import com.mopanesystems.zipos.ui.screens.products.ProductScreen
import com.mopanesystems.zipos.ui.screens.products.ProductViewModel

@Composable
fun AppNavGraph(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController,
    modifier: Any
) {
    //navController = rememberNavController()
    val isLoggedIn = authViewModel.authState != null


    NavHost(navController, startDestination = if (isLoggedIn) "dashboard" else "login") {
        composable("login") {
            LoginScreen(viewModel = authViewModel) {
                navController.navigate("dashboard") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        composable("dashboard") {
            DashboardScreen(
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }

        composable("products") {
            val productViewModel: ProductViewModel = hiltViewModel()
            ProductScreen(viewModel = productViewModel,navController, onCheckout = {
                // Navigate to checkout screen
            })
        }

        composable("editProduct/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType }))
        { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            if (productId != null) {
                EditProductScreen(productId = productId,
                    productViewModel = hiltViewModel(),
                    navController = navController
                )
            }
        }

    }
}
