package br.com.matt.allcool.cenarios.detalhes_drink

import android.content.Context
import br.com.matt.allcool.entidades.Drink

interface DetalhesDrinkContract {
    interface View {
        fun escondeCarregamento()
        fun exibeCarregamento()
        fun exibeInfo(drink : Drink)
        fun exibeAviso(msg : String)
    }

    interface Presenter {
        fun onExibeInfo(drink : Drink,context: Context)


    }
}