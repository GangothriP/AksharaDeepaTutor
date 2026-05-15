package com.example.aksharadeepatutor.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.aksharadeepatutor.ui.screens.*
import com.example.aksharadeepatutor.viewmodel.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.uiState.collectAsState()

    val currentRoute = navController.currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route ?: Screen.Welcome.route

    LaunchedEffect(authState.student) {
        if (authState.student != null && currentRoute == Screen.Welcome.route) {
            navController.navigate(Screen.Dashboard.route) {
                popUpTo(Screen.Welcome.route) { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            if (currentRoute != Screen.Welcome.route && currentRoute != Screen.Auth.route) {
                TopAppBar(
                    title = { Text(titleFor(currentRoute)) },
                    navigationIcon = {
                        if (currentRoute != Screen.Dashboard.route) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(
                    goToAuth = {
                        navController.navigate(Screen.Auth.route)
                    }
                )
            }

            composable(Screen.Auth.route) {
                AuthScreen(
                    viewModel = authViewModel,
                    goToDashboard = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Dashboard.route) {
                val viewModel: DashboardViewModel = hiltViewModel()
                DashboardScreen(
                    viewModel = viewModel,
                    navigate = { route -> navController.navigate(route) }
                )
            }

            composable(Screen.Notes.route) {
                val viewModel: NotesViewModel = hiltViewModel()
                NotesScreen(
                    viewModel = viewModel,
                    startChapterQuiz = { subject, chapter ->
                        navController.navigate(Screen.quizRoute(subject, chapter))
                    }
                )
            }

            composable(Screen.Syllabus.route) {
                val viewModel: SyllabusViewModel = hiltViewModel()
                SyllabusScreen(viewModel)
            }

            composable(
                route = Screen.Quiz.route,
                arguments = listOf(
                    navArgument("subject") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    },
                    navArgument("chapter") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backStackEntry ->
                val viewModel: QuizViewModel = hiltViewModel()
                QuizScreen(
                    viewModel = viewModel,
                    subject = backStackEntry.arguments?.getString("subject"),
                    chapter = backStackEntry.arguments?.getString("chapter"),
                    navigate = { route -> navController.navigate(route) }
                )
            }

            composable(Screen.Review.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.previousBackStackEntry ?: backStackEntry
                }
                val viewModel: QuizViewModel = hiltViewModel(parentEntry)
                ReviewScreen(viewModel)
            }

            composable(Screen.Strength.route) {
                val viewModel: DashboardViewModel = hiltViewModel()
                StrengthScreen(viewModel)
            }

            composable(Screen.Goal.route) {
                val viewModel: GoalViewModel = hiltViewModel()
                GoalScreen(viewModel)
            }

            composable(Screen.Gap.route) {
                val viewModel: DashboardViewModel = hiltViewModel()
                GapAnalysisScreen(viewModel)
            }
        }
    }
}

private fun titleFor(route: String): String {
    return when (route) {
        Screen.Notes.route -> "Study Notes"
        Screen.Syllabus.route -> "Syllabus"
        Screen.Quiz.route -> "Quiz"
        Screen.Review.route -> "Review"
        Screen.Strength.route -> "Strength"
        Screen.Goal.route -> "Daily Goal"
        Screen.Gap.route -> "Gap Analysis"
        else -> "Akshara-Deepa"
    }
}
