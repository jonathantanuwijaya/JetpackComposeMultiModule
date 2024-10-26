package com.learndev.calorytracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.learndev.calorytracker.ui.theme.CaloryTrackerTheme
import com.learndev.core.domain.preferences.Preferences
import com.learndev.calorytracker.navigation.Route
import com.learndev.onboarding_presentation.activity.ActivityScreen
import com.learndev.onboarding_presentation.activity.GoalScreen
import com.learndev.onboarding_presentation.age.AgeScreen
import com.learndev.onboarding_presentation.gender.GenderScreen
import com.learndev.onboarding_presentation.height.HeightScreen
import com.learndev.onboarding_presentation.height.WeightScreen
import com.learndev.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.learndev.onboarding_presentation.welcome.WelcomeScreen
import com.learndev.tracker_presentation.search.SearchScreen
import com.learndev.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnBoarding = preferences.loadShouldShowOnBoarding()
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = if (shouldShowOnBoarding) {
                            Route.WELCOME
                        } else Route.TRACKER_OVERVIEW
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNextClick = { navController.navigate(Route.AGE) })
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = { navController.navigate(Route.GENDER) }
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNextClick = { navController.navigate(Route.HEIGHT) })
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                { navController.navigate(Route.WEIGHT) }
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                { navController.navigate(Route.ACTIVITY) }
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                { navController.navigate(Route.TRACKER_OVERVIEW) }
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen({ navController.navigate(Route.GOAL) })
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNextClick = { navController.navigate(Route.NUTRIENT_GOAL) })
                        }

                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(
                                onNavigate = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                onNavigateUp = { navController.navigateUp() },
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year
                            )
                        }
                    }
                }

            }
        }
    }
}