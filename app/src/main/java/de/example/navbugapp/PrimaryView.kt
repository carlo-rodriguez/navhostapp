package de.example.navbugapp

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PrimaryView(primaryCoordinator: PrimaryCoordinator, mainCoordinator: MainCoordinator) {
    primaryCoordinator.navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNav(primaryCoordinator.currentScreen.ordinal) { index -> primaryCoordinator.switchTab(index) } },
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            navController = primaryCoordinator.navController,
            startDestination = primaryCoordinator.currentScreen.route
        ) {
            composable(BottomNavigationRoute.FEED.route) {
                Feed()
            }
            composable(BottomNavigationRoute.TEST.route) {
                Test(mainCoordinator)
            }
            composable(BottomNavigationRoute.PROFILE.route) {
                Profile()
            }
        }
    }
}

class PrimaryCoordinator {

    lateinit var navController: NavHostController
    var currentScreen = BottomNavigationRoute.FEED

    fun switchTab(index: Int){
        val screen = when (index) {
            0 -> BottomNavigationRoute.FEED
            1 -> BottomNavigationRoute.TEST
            2 -> BottomNavigationRoute.PROFILE
            else -> BottomNavigationRoute.FEED
        }
        currentScreen = screen
        navController.navigate(screen.route)
        {
            restoreState = true
            popUpTo(findStartDestination(navController.graph).id) {
                saveState = true
            }
        }
    }
}

val NavGraph.startDestination: NavDestination? get() = findNode(startDestinationId)
tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}

@Composable
fun Feed() {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        FeedList(title = "First List")
        FeedList(title = "Second List")
        FeedList(title = "Third List")
        FeedList(title = "Fourth List")
    }
}

@Composable
fun Test(mainCoordinator: MainCoordinator) {
    Column {
        Text(
            text = "Test",
            modifier = Modifier
        )
        Button(onClick = {
            mainCoordinator.toSecondary()
        }) {
            Text("to Secondary")
        }
    }
}

@Composable
fun Profile() {
    Column {
        Text(
            text = "Profile",
            modifier = Modifier
        )
    }
}
