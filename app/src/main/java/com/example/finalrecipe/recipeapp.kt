package com.example.finalrecipe



import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun recipeapp(navController: NavHostController){
    val recipeViewModel:MainViewModel= viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController,
        startDestination = screen.RecipeScreen.route){
        composable(route=screen.RecipeScreen.route){
            //This part is responsible for passing data from the current screen to the detail screen
            //it utilizes the savedstatehandle, which is a componenet of the compose navigation framework
            RecipeScreen(viewState = viewstate, navigatetodetail ={navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(screen.detailScreen.route)} )
        }
        composable(route=screen.detailScreen.route){
            val category=navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat")?: Category("","","","")
            CategoryDetailScreen(category = category)
        }

    }
}