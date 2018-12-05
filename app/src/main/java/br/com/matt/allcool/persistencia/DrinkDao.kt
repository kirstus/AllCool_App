package br.com.matt.allcool.persistencia

import android.arch.persistence.room.*
import br.com.matt.allcool.entidades.Drink

@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(drink: Drink)

    @Query("SELECT * FROM drink")
    fun getAll(): List<Drink>

    @Delete
    fun delete(drink: Drink)

    @Query("SELECT * FROM drink WHERE id = :drinkId LIMIT 1")
    fun getDrink(drinkId: Int): Drink

    @Query("SELECT * FROM drink WHERE nome like :drinkNome")
    fun findByName(drinkNome: String): List<Drink>

    @Query("SELECT * FROM drink WHERE favorito = 1")
    fun getFavorites(): List<Drink>

    @Query("SELECT * FROM drink WHERE categoria = :drinkCategoria")
    fun getInCategory(drinkCategoria: String): List<Drink>
}