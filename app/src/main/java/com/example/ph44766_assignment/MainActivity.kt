package com.example.ph44766_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ph44766_assignment.model.DetailPopularScreen
import com.example.ph44766_assignment.model.ViewModels.CartViewModel
import com.example.ph44766_assignment.model.ViewModels.FavoriteViewModel
import com.example.ph44766_assignment.navigation.BottomNavigation
import com.example.ph44766_assignment.screens.CartScreen
import com.example.ph44766_assignment.screens.CheckOutScreen
import com.example.ph44766_assignment.screens.FavoriteScreen
import com.example.ph44766_assignment.screens.ForgotPasswordScreen
import com.example.ph44766_assignment.screens.LoginScreen
import com.example.ph44766_assignment.screens.MyOrderScreen
import com.example.ph44766_assignment.screens.NotificationScreen
import com.example.ph44766_assignment.screens.PaymentMethodScreen
import com.example.ph44766_assignment.screens.PersonScreen
import com.example.ph44766_assignment.screens.PopularsScreen
import com.example.ph44766_assignment.screens.ShippingAddressScreen
import com.example.ph44766_assignment.screens.SignUpScreen
import com.example.ph44766_assignment.screens.SuccessScreen
import com.example.ph44766_assignment.screens.WelcomeScreen
import com.example.ph44766_assignment.ui.theme.AsmKOT104Theme
import com.example.ph44766_assignment.user.login.LoginViewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsmKOT104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "welcome") {
                        composable("welcome") {
                            WelcomeScreen(navController = navController)
                        }
                        composable("login") {
                            LoginScreen(
                                navController = navController,
                                loginViewModel = loginViewModel
                            )
                        }
                        composable("forgot") {
                            ForgotPasswordScreen(navController = navController)
                        }
                        composable("signup") {
                            SignUpScreen(
                                navController = navController,
                                loginViewModel = loginViewModel
                            )
                        }
                        composable("bottom") {
                            BottomNavigation(navController = navController)
                        }
                        composable("favorite") {
                            FavoriteScreen(
                                navControl = navController,
//                                favoriteViewModel = favoriteViewModel
                            )
                        }
                        composable("cart") {
                            CartScreen(navControl = navController, cartViewModel = cartViewModel)
                        }
                        composable("person") {
                            PersonScreen(navController = navController)
                        }
                        composable("notification") {
                            NotificationScreen(navController = navController)
                        }
                        composable("checkOutScreen") {
                            CheckOutScreen(navControl = navController, data = null)
                        }
                        composable("successScreen") {
                            SuccessScreen(navControl = navController)
                        }
                        composable("myOrderScreen") {
                            MyOrderScreen(navController = navController)
                        }
//                        composable("addPayMethod"){
//                            AddPaymentMethod(navControl = navController, data = null)
//                        }
//                        composable("addShippingAddress"){
//                            AddShippingAddress(navControl = navController)
//                        }
                        composable("payMethod") {
                            PaymentMethodScreen(navControl = navController)
                        }
                        composable("shippingAddressScreen") {
                            ShippingAddressScreen(navControl = navController)
                        }
                        composable("PopularsScreen") {
                            PopularsScreen(navControl = navController)
                        }
                        composable(
                            route = "detail/{popularsId}",
                            arguments = listOf(navArgument("popularsId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val bedId =
                                backStackEntry.arguments?.getInt("popularsId") ?: return@composable
                            DetailPopularScreen(
                                navController, bedId,
//                                favoriteViewModel = favoriteViewModel,
                                cartViewModel = cartViewModel
//                                popularViewModel = popularViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}


