package br.com.matt.allcool.cenarios.detalhes_drink

import android.content.Context
import android.view.View
import br.com.matt.allcool.R
import br.com.matt.allcool.entidades.DrinksList
import br.com.matt.allcool.network.RetrofitInit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesDrinkPresenter(val view : DetalhesDrinkContract.View) : DetalhesDrinkContract.Presenter{
    override fun onExibeInfo(drinkID : String) {
        val drinksService = RetrofitInit().createDrinksService()

        val call = drinksService.getDrink(drinkID)

        call.enqueue(object : Callback<DrinksList> {
            override fun onFailure(call: Call<DrinksList>, t: Throwable) {

                view.escondeCarregamento()
                view.exibeAviso("Falha na conexão. Verifique o acesso a internet")
            }

            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                view.escondeCarregamento()
                if(response.body() != null){
                    view.exibeInfo(response.body()!!.drinks.first())
                    //view.exibeAviso(response.body()!!.drinks.first().toString())
                }else {
                    view.exibeAviso("Drink não encontrado")
                }
            }
        })
    }
}