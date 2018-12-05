package br.com.matt.allcool.persistencia

import android.arch.persistence.room.*
import br.com.matt.allcool.entidades.Drink

@Dao
interface DrinksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(drink: Drink)

    @Query("SELECT * FROM drink")
    fun getAll(): List<Drink>

    @Query("SELECT * FROM drink LIMIT 5")
    fun getFive(): List<Drink>

    @Update
    fun update(drink: Drink)

    @Delete
    fun delete(drink: Drink)

    @Query("SELECT * FROM drink WHERE id = :drinkId")
    fun getDrink(drinkId: String): Drink

    @Query("SELECT * FROM drink WHERE nome like :drinkNome  LIMIT 1")
    fun findByName(drinkNome: String): Drink

    @Query("SELECT * FROM drink WHERE favorito = 1")
    fun getFavorites(): List<Drink>

    @Query("SELECT * FROM drink WHERE categoria = :drinkCategoria")
    fun getInCategory(drinkCategoria: String): List<Drink>
}