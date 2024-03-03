package de.example.navbugapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainCoordinator = MainCoordinator(rememberNavController())
            val primaryCoordinator = PrimaryCoordinator()
            NavHost(
                navController = mainCoordinator.navController,
                startDestination = MainRoute.PRIMARY.route,
            ) {
                composable(MainRoute.PRIMARY.route) {
                    PrimaryView(primaryCoordinator, mainCoordinator)
                }
                composable(MainRoute.SECONDARY.route){
                    SecondaryView(mainCoordinator)
                }
            }
        }
    }
}

class MainCoordinator(val navController: NavHostController) {
    fun toPrimary(){
        navController.navigateUp()
        //navController.navigate(MainRoute.PRIMARY.route)
    }
    fun toSecondary(){
        navController.navigate(MainRoute.SECONDARY.route)
    }
}

enum class MainRoute(val route: String) {
    PRIMARY("PRIMARY"),
    SECONDARY("SECONDARY"),
}
