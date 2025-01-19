package com.github.kkoscielniak.usersapp.users
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    navController: NavController,
    userId: Int,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()

    LaunchedEffect(userId) {
        viewModel.fetchUserById(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        user?.let {
            UserDetailsContent(user = it, modifier = Modifier.padding(padding))
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun UserDetailsContent(user: UserDetails, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Name: ${user.name}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text("Username: @${user.username}", style = MaterialTheme.typography.bodyMedium)
                Text("Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
                Text("Phone: ${user.phone}", style = MaterialTheme.typography.bodyMedium)
                Text("Website: ${user.website}", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Address:", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text("${user.address.street}, ${user.address.suite}", style = MaterialTheme.typography.bodyMedium)
                Text("${user.address.city} - ${user.address.zipcode}", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Company:", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text(user.company.name, style = MaterialTheme.typography.bodyMedium)
                Text(user.company.catchPhrase, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}
