package br.com.matt.allcool.cenarios.splash_screen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.matt.allcool.R
import br.com.matt.allcool.cenarios.lista_drinks.ListaDrinksActivity
import br.com.matt.allcool.utilitarios.GlideApp
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



        Handler().postDelayed({
            val listaContatinhos = Intent(this, ListaDrinksActivity::class.java)
            startActivity(listaContatinhos)
            finish()
        }, 2000)

    }
}
