package com.example.dbsqliterevisao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "database.db", null, 1) {

    val sql = arrayOf(
        "CREATE TABLE nomes (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT)",
        "INSERT INTO nomes (nome) VALUES ('teste')",
        "INSERT INTO nomes (nome) VALUES ('teste2')"
    )

    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE nomes")
        onCreate(db)
    }

    fun insertNome(nome: String) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nome", nome)

        val res = db.insert("nomes", null, contentValues)
        db.close()

        return res
    }

    fun updateNome(id: Int, nome: String) : Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put("nome", nome)
        val res = db.update("nomes", contentValues, "id=?", arrayOf(id.toString()))
        db.close()

        return res
    }

    fun deleteNome(id: Int) : Int{
        val db = this.writableDatabase
        val res = db.delete("nomes", "id=?", arrayOf(id.toString()))
        db.close()

        return res
    }

    fun nomesSelectAll() : ArrayList<Nomes> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM nomes", null)
        val listaNome : ArrayList<Nomes> = ArrayList()

        if (c.count > 0) {
            c.moveToFirst()
            do {
                val idIndex = c.getColumnIndex("id")
                val nomeIndex = c.getColumnIndex("nome")

                val id = c.getInt(idIndex)
                val nome = c.getString(nomeIndex)

                listaNome.add(Nomes(id, nome))
            } while (c.moveToNext())
        }

        db.close()

        return listaNome
    }
}