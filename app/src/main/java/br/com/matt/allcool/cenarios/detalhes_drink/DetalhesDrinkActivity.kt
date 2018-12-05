package br.com.matt.allcool.cenarios.detalhes_drink

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.matt.allcool.R
import br.com.matt.allcool.entidades.Drink
import br.com.matt.allcool.utilitarios.GlideApp
import kotlinx.android.synthetic.main.activity_detalhes_drink.*

class DetalhesDrinkActivity : AppCompatActivity(), DetalhesDrinkContract.View {

    companion object {
        public const val DRINK_DETAIL: String = "drink_detalhado"
    }

    val presenter: DetalhesDrinkContract.Presenter = DetalhesDrinkPresenter(this)
    var drinkDetalhado : Drink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_drink)

        drinkDetalhado = intent.getSerializableExtra(DRINK_DETAIL) as Drink?
        if(drinkDetalhado != null)
            presenter.onExibeInfo(drinkDetalhado!!.id)




    }


    override fun exibeInfo(drinkDetalhado: Drink) {
        val ingredientes = listOf(ingrediente1,ingrediente2,ingrediente3,ingrediente4,ingrediente5,ingrediente6,ingrediente7,ingrediente8,ingrediente9,ingrediente10)
        val strIngredientes = listOf(drinkDetalhado.strIngredient1,drinkDetalhado.strIngredient2,drinkDetalhado.strIngredient3,drinkDetalhado.strIngredient4,drinkDetalhado.strIngredient5,drinkDetalhado.strIngredient6,drinkDetalhado.strIngredient7,drinkDetalhado.strIngredient8,drinkDetalhado.strIngredient9,drinkDetalhado.strIngredient10)
        val medidas = listOf(medida1,medida2,medida3,medida4,medida5,medida6,medida7,medida8,medida9,medida10)
        val strMedidas = listOf(drinkDetalhado.strMeasure1,drinkDetalhado.strMeasure2,drinkDetalhado.strMeasure3,drinkDetalhado.strMeasure4,drinkDetalhado.strMeasure5,drinkDetalhado.strMeasure6,drinkDetalhado.strMeasure7,drinkDetalhado.strMeasure8,drinkDetalhado.strMeasure9,drinkDetalhado.strMeasure10)

        for(pos in 0..9){
            if (strIngredientes[pos] != "" && strMedidas[pos] != "" ){
                ingredientes[pos].text = strIngredientes[pos]
                ingredientes[pos].visibility = View.VISIBLE
                medidas[pos].text = strMedidas[pos]
                medidas[pos].visibility = View.VISIBLE
            }
        }


        nome.text = drinkDetalhado.nome
        categoria.text = drinkDetalhado.categoria
        alcoolico.text = drinkDetalhado.alcoolico
        categoria.text = drinkDetalhado.categoria
        if(drinkDetalhado.classificacao != null){
            classificacao.text = drinkDetalhado.classificacao
            classificacao.visibility = View.VISIBLE
        }

        instrucoes.text = drinkDetalhado.strInstructions

        val thumbnail = GlideApp.with(this@DetalhesDrinkActivity)
                .load(R.drawable.ic_launcher_background)

        GlideApp.with(this@DetalhesDrinkActivity)
                .load(drinkDetalhado.thumbnail)
                .thumbnail(thumbnail)
                .centerCrop()
                .into(imgDrink)
    }

    override fun exibeAviso(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
    override fun exibeCarregamento() {
        val b =1
        //
    }

    override fun escondeCarregamento() {
        val a = 2
        //
    }
}
