package br.com.matt.allcool.cenarios.detalhes_drink

import android.content.Context
import br.com.matt.allcool.entidades.Drink
import br.com.matt.allcool.entidades.DrinksList
import br.com.matt.allcool.network.RetrofitInit
import br.com.matt.allcool.persistencia.AppDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesDrinkPresenter(val view : DetalhesDrinkContract.View) : DetalhesDrinkContract.Presenter{
    override fun onExibeInfo(drink : Drink,context: Context) {

        val drinksDao = AppDatabase.getInstance(context).drinksDao()
        var drinkDetalhado : Drink?
        doAsync {
            drinkDetalhado = drinksDao.getDrink(drink.id)
            uiThread {
                if (drinkDetalhado!=null) {
                        view.exibeInfo(drinkDetalhado!!)
                    if (drinkDetalhado!!.categoria == "" && drinkDetalhado!!.alcoolico == "")
                        buscaNaNuvem(context,drink)
                } else {
                    buscaNaNuvem(context,drink)
                }
            }
        }
    }

    private fun buscaNaNuvem(context: Context,drink : Drink){
        view.exibeCarregamento()

        val drinksService = RetrofitInit().createDrinksService()
        val call = drinksService.getDrink(drink.id)

        call.enqueue(object : Callback<DrinksList> {
            override fun onFailure(call: Call<DrinksList>, t: Throwable) {
                view.escondeCarregamento()
                view.exibeAviso("Falha na conexão. Verifique o acesso a internet")
            }

            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                view.escondeCarregamento()
                if(response.body() != null){
                    val drinksDao = AppDatabase.getInstance(context).drinksDao()
                    var drink : Drink = response.body()!!.drinks.first()
                    if(drink.classificacao == null)
                        drink.classificacao = ""
                    doAsync {
                        drinksDao.insert(drink)
                    }
                    view.exibeInfo(drink)
                }else {
                    view.exibeAviso("Drink não encontrado")
                }
            }
        })
    }

}