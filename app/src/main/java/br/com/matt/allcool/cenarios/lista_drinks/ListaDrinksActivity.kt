package br.com.matt.allcool.cenarios.lista_drinks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.matt.allcool.R
import br.com.matt.allcool.cenarios.detalhes_drink.DetalhesDrinkActivity
import br.com.matt.allcool.cenarios.detalhes_drink.DetalhesDrinkActivity.Companion.DRINK_DETAIL
import br.com.matt.allcool.entidades.Drink
import kotlinx.android.synthetic.main.activity_lista_drinks.*

class ListaDrinksActivity : AppCompatActivity(), ListaDrinksContract.View {

    val presenter: ListaDrinksContract.Presenter = ListaDrinksPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_drinks)

        presenter.onLoadLista()

    }

    override fun exibeCarregamento() {
        val b =1
        //
    }

    override fun escondeCarregamento() {
        val a = 2
        //
    }

    override fun exibeLista(drinks : List<Drink>, item_layout : Int) {
        val adapter = ListaDrinksAdapter(this, drinks as MutableList<Drink>, item_layout)

        adapter.setOnItemClickListener {position ->
            val detalhes = Intent(this, DetalhesDrinkActivity::class.java)
            detalhes.putExtra(DRINK_DETAIL,drinks.get(position))
            startActivity(detalhes)
        }

        adapter.configuraCliqueLongo(presenter.onLongClick(drinks))
        rvAlcoolicos.adapter = adapter

        rvAlcoolicos.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun exibeAviso(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}
