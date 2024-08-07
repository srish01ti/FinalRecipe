package com.example.finalrecipe


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel:ViewModel() {


    private  val _categorystate= mutableStateOf(Recipestate())
    val categoriesState:State<Recipestate> =_categorystate

    init {
        fetchcategories()
    }

    private  fun fetchcategories(){
        viewModelScope.launch {
            try {
                val response= recipeService.getCategories()
                _categorystate.value=_categorystate.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }catch (e:Exception){
                _categorystate.value=_categorystate.value.copy(
                    loading = false,
                    error="Error fetching categories ${e.message}"
                )
            }
        }
    }


    data class Recipestate(
        val loading:Boolean=true,
        val list: List<Category> = emptyList(),
        val error:String?=null
    )
}