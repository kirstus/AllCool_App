package br.com.matt.allcool.cenarios.lista_drinks

import android.content.Context
import br.com.matt.allcool.entidades.Drink

interface ListaDrinksContract {

    interface View {
        fun exibeCarregamento()
        fun exibeLista()
    }

    interface Presenter {
        fun onLoadLista()
    }
}