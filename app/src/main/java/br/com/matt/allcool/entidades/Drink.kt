package br.com.matt.allcool.entidades

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Drink(
        @PrimaryKey
        @SerializedName("idDrink")
        var id: String = "0",
        @SerializedName("strDrink")
        var nome : String = "",
        @SerializedName("strCategory")
        var categoria : String = "",
        @SerializedName("strIBA")
        var classificacao : String = "",
        @SerializedName("strAlcoholic")
        var alcoolico : String = "",
        @SerializedName("strGlass")
        var copo : String = "",
        var strInstructions : String = "",
        @SerializedName("strDrinkThumb")
        var thumbnail: String = "",
        var strIngredient1 : String = "",
        var strIngredient2 : String = "",
        var strIngredient3 : String = "",
        var strIngredient4 : String = "",
        var strIngredient5 : String = "",
        var strIngredient6 : String = "",
        var strIngredient7 : String = "",
        var strIngredient8 : String = "",
        var strIngredient9 : String = "",
        var strIngredient10 : String = "",
        var strMeasure1 : String = "",
        var strMeasure2 : String = "",
        var strMeasure3 : String = "",
        var strMeasure4 : String = "",
        var strMeasure5 : String = "",
        var strMeasure6 : String = "",
        var strMeasure7 : String = "",
        var strMeasure8 : String = "",
        var strMeasure9 : String = "",
        var strMeasure10 : String = "",
        @SerializedName("dateModified")
        var dateModified : String = "",
        var favorito : Int = 0
) : Serializable
