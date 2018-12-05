package br.com.matt.allcool.cenarios.lista_drinks

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaDrinksPresenter(val view : ListaDrinksContract.View) : ListaDrinksContract.Presenter {

    override fun onLoadLista(){

        view.exibeCarregamento()

        //retrofit
        //view.exibeLista()
    }


}