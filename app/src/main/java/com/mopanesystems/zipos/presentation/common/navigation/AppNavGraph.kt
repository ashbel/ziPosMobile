package com.mopanesystems.zipos.presentation.common.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mopanesystems.zipos.presentation.auth.ui.LoginScreen
import com.mopanesystems.zipos.presentation.auth.viewmodel.AuthViewModel
import com.mopanesystems.zipos.presentation.dashboard.ui.DashboardScreen
import com.mopanesystems.zipos.presentation.inventory.ui.EditInventoryScreen
import com.mopanesystems.zipos.presentation.inventory.ui.InventoryScreen
import com.mopanesystems.zipos.presentation.inventory.viewmodel.InventoryViewModel

@Composable
fun AppNavGraph(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController,
    modifier: Any
) {
    //navController = rememberNavController()
    val isLoggedIn = authViewModel.authState != null
    Log.d("TAG", "AppNavGraph: " + isLoggedIn)

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
            val productViewModel: InventoryViewModel = hiltViewModel()
            InventoryScreen(
                viewModel = productViewModel,
                onAddClick = {
                    navController.navigate("addProduct")
                },
                onEditClick = { product ->
                    navController.navigate("editProduct/${product.id}")
                },
                navController = navController
            )
        }

        composable("editProduct/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType }))
        { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val productViewModel: InventoryViewModel = hiltViewModel()
            EditInventoryScreen(productId = productId,
                onSaved = {
                    navController.navigate("products") {
                        popUpTo("editProduct/$productId") { inclusive = true }
                    }
                }, productViewModel)
        }

        composable("addProduct") {
            val productViewModel: InventoryViewModel = hiltViewModel()
            EditInventoryScreen(onSaved = {
                navController.navigate("products") {
                    popUpTo("addProduct") { inclusive = true }
                }
            }, viewModel = productViewModel)
        }

    }
}
