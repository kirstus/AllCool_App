package br.com.matt.allcool.entidades

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Drink(
        @PrimaryKey
        @SerializedName("idDrink")
        val id: String = "0",
        @SerializedName("strDrink")
        val nome : String = "",
        @SerializedName("strCategory")
        val categoria : String = "",
        @SerializedName("strIBA")
        val classificacao : String = "",
        @SerializedName("strAlcoholic")
        val alcoolico : String = "",
        @SerializedName("strGlass")
        val copo : String = "",
        val strInstructions : String = "",
        @SerializedName("strDrinkThumb")
        val thumbnail: String = "",
        val strIngredient1 : String = "",
        val strIngredient2 : String = "",
        val strIngredient3 : String = "",
        val strIngredient4 : String = "",
        val strIngredient5 : String = "",
        val strIngredient6 : String = "",
        val strIngredient7 : String = "",
        val strIngredient8 : String = "",
        val strIngredient9 : String = "",
        val strIngredient10 : String = "",
        val strMeasure1 : String = "",
        val strMeasure2 : String = "",
        val strMeasure3 : String = "",
        val strMeasure4 : String = "",
        val strMeasure5 : String = "",
        val strMeasure6 : String = "",
        val strMeasure7 : String = "",
        val strMeasure8 : String = "",
        val strMeasure9 : String = "",
        val strMeasure10 : String = "",
        @SerializedName("dateModified")
        val dateModified : String = "",
        var favorito : Int = 0
) : Serializable
