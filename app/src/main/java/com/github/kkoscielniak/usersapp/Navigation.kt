package com.github.kkoscielniak.usersapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.kkoscielniak.usersapp.users.UserDetailsScreen
import com.github.kkoscielniak.usersapp.users.UsersScreen

sealed class Screen(val route: String) {
    object UsersList : Screen("users_list")
    object UserDetails : Screen("user_details/{id}") {
        fun createRoute(id: Int) = "user_details/$id"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.UsersList.route) {
        composable(Screen.UsersList.route) {
            UsersScreen(navController)
        }
        composable(Screen.UserDetails.route) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (userId != null) {
                UserDetailsScreen(navController, userId)
            }
        }
    }
}
