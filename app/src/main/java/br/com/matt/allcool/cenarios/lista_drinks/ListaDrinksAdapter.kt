package br.com.matt.allcool.cenarios.lista_drinks

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.matt.allcool.R
import br.com.matt.allcool.entidades.Drink
import br.com.matt.allcool.utilitarios.GlideApp
import kotlinx.android.synthetic.main.item_drink_vertical.view.*


class ListaDrinksAdapter(val context: Context, val drinks : MutableList<Drink>, val item_layout : Int ) : RecyclerView.Adapter<ListaDrinksAdapter.ViewHolder>() {

    //salva a função do clique no item
    var clickListener: ((index: Int) -> Unit)? = null
    //salva a função do clique longo do item
    var cliqueLongoListener: ((index: Int) -> Boolean)? = null


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(item_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    fun setOnItemClickListener(clique: ((index: Int) -> Unit)){
        this.clickListener = clique
    }

    fun configuraCliqueLongo(cliqueLongo: ((index: Int) -> Boolean)){
        this.cliqueLongoListener = cliqueLongo
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, posicao: Int) {
        viewHolder.bindView(context, drinks[posicao], clickListener, cliqueLongoListener)
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindView(context : Context, drink : Drink,
                     clickListener : ((index : Int) -> Unit)?,
                     longClickListener : ((index: Int) -> Boolean)?){

            itemView.nome.text = drink.nome
            itemView.categoria.text = drink.categoria
            itemView.alcoolico.text = drink.alcoolico
            itemView.classificacao.text = drink.classificacao

            val thumbnail = GlideApp.with(context)
                    .load(R.drawable.ic_launcher_background)

            GlideApp.with(context)
                    .load(drink.thumbnail)
                    .thumbnail(thumbnail)
                    .centerCrop()
                    .into(itemView.imgDrink)

            if(clickListener != null) {
                itemView.setOnClickListener {
                    clickListener.invoke(adapterPosition)
                }
            }

            if(longClickListener != null){
                itemView.setOnLongClickListener{
                    longClickListener.invoke((adapterPosition))
                }
            }


        }
    }
}