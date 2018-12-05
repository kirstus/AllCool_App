package br.com.matt.allcool.network

import br.com.matt.allcool.entidades.DrinksList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksService {

    companion object {
        private const val API_KEY = "1"
    }

    @GET("$API_KEY/lookup.php?")
    fun getDrink(@Query("i") i: String = "13060"): Call<DrinksList>

    @GET("$API_KEY/filter.php?")
    fun getAlcoholic(@Query("a") alcoholic: String = "Alcoholic"): Call<DrinksList>

    @GET("$API_KEY/filter.php?")
    fun getCategory(@Query("c") category: String = "Ordinary_Drink"): Call<DrinksList>

    @GET("$API_KEY/random.php?")
    fun getRandom(): Call<DrinksList>
    /*
    @GET("everything?$API_KEY")
    fun getEverything(@Query("q") query: String): Call<DrinksList>*/

}