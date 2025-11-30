package com.kshitiz.mydoctorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kshitiz.mydoctorapp.model.DoctorRepository
import com.kshitiz.mydoctorapp.screens.DoctorDetailScreen
import com.kshitiz.mydoctorapp.screens.HomeScreen
import com.kshitiz.mydoctorapp.screens.OnBoardingScreen
import com.kshitiz.mydoctorapp.ui.theme.MyDoctorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyDoctorAppTheme {
                AppNav()
            }
        }
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.OnBoarding){
        composable(Routes.OnBoarding){
            OnBoardingScreen{
                navController.navigate(Routes.Home){
                    popUpTo(Routes.OnBoarding) { inclusive = true }
                }
            }
        }

        composable(Routes.Home) {
            HomeScreen(
                onDoctorClick = { doctor ->
                    navController.navigate("doctor_detail/${doctor.id}")
                }
            )
        }

        composable(
            route = Routes.DoctorDetail,
            arguments = listOf(navArgument("doctorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getInt("doctorId")
            val doctor = DoctorRepository.getDoctorById(doctorId ?: 0)
            if (doctor != null) {
                DoctorDetailScreen(
                    doctor = doctor,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }

}

