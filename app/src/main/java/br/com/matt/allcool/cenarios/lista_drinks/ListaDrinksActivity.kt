package br.com.matt.allcool.cenarios.lista_drinks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
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

        presenter.onLoadLista(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menuAtualizar -> presenter.atualizaLista(this)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun exibeCarregamento() {
        carregamento.visibility = View.VISIBLE
    }

    override fun escondeCarregamento() {
        carregamento.visibility = View.INVISIBLE
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
