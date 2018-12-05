package br.com.matt.allcool.cenarios.lista_drinks

import android.content.Context
import br.com.matt.allcool.entidades.Drink

interface ListaDrinksContract {

    interface View {
        fun exibeCarregamento()
        fun escondeCarregamento()
        fun exibeLista(drinks : List<Drink>, item_layout : Int)
        fun exibeAviso(msg : String)

    }

    interface Presenter {
        fun onLongClick(drinks : List<Drink>) : ((index: Int) -> Boolean)
        fun onLoadLista(context: Context)
        fun atualizaLista(context: Context)
    }
}