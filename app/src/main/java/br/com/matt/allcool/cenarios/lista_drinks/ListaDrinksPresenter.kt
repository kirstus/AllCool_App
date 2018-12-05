package br.com.matt.allcool.cenarios.lista_drinks

import android.content.Context
import br.com.matt.allcool.R
import br.com.matt.allcool.entidades.Drink
import br.com.matt.allcool.entidades.DrinksList
import br.com.matt.allcool.network.RetrofitInit
import br.com.matt.allcool.persistencia.AppDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaDrinksPresenter(val view : ListaDrinksContract.View) : ListaDrinksContract.Presenter {

    override fun getRandom(context: Context){
        view.exibeCarregamento()
        view.exibeAviso("Buscando da internet")
        val drinksService = RetrofitInit().createDrinksService()

        val call = drinksService.getRandom()

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
                    view.chamaDetalhes(drink)
                }else {
                    view.exibeAviso("Drink não encontrado")
                }
            }
        })
    }

    override fun onLoadLista(context: Context){

        view.exibeCarregamento()

        val drinksDao= AppDatabase.getInstance(context).drinksDao()
        doAsync {
            val listaDrinks = drinksDao.getAll()
            uiThread {
                if (!listaDrinks.isEmpty()) {
                    view.exibeCarregamento()
                    view.exibeAviso(listaDrinks.first().toString())
                    view.exibeLista(listaDrinks, R.layout.item_drink_vertical)
                } else {
                    buscaNaNuvem(context)
                }
            }
        }

    }

    override fun atualizaLista(context: Context) = buscaNaNuvem(context)

    private fun buscaNaNuvem(context: Context) {
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
                if (response.body() != null) {
                    val drinksDao= AppDatabase.getInstance(context).drinksDao()
                    for(drink in response.body()!!.drinks){
                        doAsync {
                            drinksDao.insert(drink)
                        }
                    }

                    view.exibeLista(response.body()!!.drinks, R.layout.item_drink_vertical)
                } else {
                    view.escondeCarregamento()
                    view.exibeAviso("Nenhum drink encontrado")
                }
            }
        })
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