package br.com.matt.allcool.cenarios.detalhes_drink

import android.content.Context

interface DetalhesDrinkContract {
    interface View {
        fun exibeCarregamento()
        fun exibeInfo()
    }

    interface Presenter {
        fun onExibeInfo(context: Context)
    }
}