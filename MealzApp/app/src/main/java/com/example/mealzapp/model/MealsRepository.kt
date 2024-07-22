package com.example.mealzapp.model

import com.example.mealzapp.model.api.MealsWebService
import com.example.mealzapp.model.response.MealCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals(): MealCategoriesResponse {
       // return webService.getMeals().execute().body() // Bad practice
        return webService.getMeals()
    }
}