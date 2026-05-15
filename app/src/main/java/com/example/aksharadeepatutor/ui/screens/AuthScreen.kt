package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.viewmodel.AuthViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    goToDashboard: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.signedIn) {
        if (state.signedIn) goToDashboard()
    }

    var isSignUp by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf("") }
    var studentClass by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isSignUp) "Create Student Account" else "Sign In",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Student Name") },
            modifier = Modifier.fillMaxWidth()
        )

        if (isSignUp) {
            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = studentClass,
                onValueChange = { studentClass = it },
                label = { Text("Class") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = school,
                onValueChange = { school = it },
                label = { Text("School") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        state.error?.let {
            Spacer(Modifier.height(10.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(18.dp))

        Button(
            onClick = {
                if (isSignUp) {
                    viewModel.signUp(name, studentClass, school, password)
                } else {
                    viewModel.signIn(name, password)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSignUp) "Sign Up" else "Sign In")
        }

        TextButton(
            onClick = { isSignUp = !isSignUp },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (isSignUp) "Already signed up? Sign In"
                else "New student? Sign Up"
            )
        }
    }
}
