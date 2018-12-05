package br.com.matt.allcool.cenarios.lista_drinks

import br.com.matt.allcool.R
import br.com.matt.allcool.entidades.Drink
import br.com.matt.allcool.entidades.DrinksList
import br.com.matt.allcool.network.RetrofitInit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaDrinksPresenter(val view : ListaDrinksContract.View) : ListaDrinksContract.Presenter {

    override fun onLoadLista(){

        view.exibeCarregamento()

        val drinksService = RetrofitInit().createDrinksService()

        val call = drinksService.getAlcoholic()

        call.enqueue(object : Callback<DrinksList> {
            override fun onFailure(call: Call<DrinksList>, t: Throwable) {
                view.escondeCarregamento()
                view.exibeAviso("Falha na conexão. Verifique o acesso a internet")
            }

            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                view.escondeCarregamento()
                if(response.body() != null){
                    view.exibeLista(response.body()!!.drinks, R.layout.item_drink_vertical)
                }else {
                    view.exibeAviso("Nenhum drink encontrado")
                }
            }
        })
        //retrofit
        //view.exibeLista()
    }

    override fun onLongClick(drinks : List<Drink>): (index: Int) -> Boolean {
        return {position->
        //val drinkdao = AppDatabase.getInstance(this).drinkDao()
        var favdrink = drinks.get(position)
        favdrink.favorito = 1
        /*doAsync {
            drinkdao.insert(favdrink)
            val aa = drinkdao.getDrink(favdrink.id)
            showMessage(aa.toString())
        }*/
         true
        }
    }


}