package com.example.dbsqliterevisao.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dbsqliterevisao.model.Usuarios

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "database2.db", null, 1) {

    var listaUsuarios = ArrayList<Usuarios>()

    val sql = arrayOf(
        "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT)",
        "INSERT INTO usuarios (nome) VALUES ('teste')",
        "INSERT INTO usuarios (nome) VALUES ('teste2')"
    )

    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE usuarios")
        onCreate(db)
    }

    fun insertNome(nome: String) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nome", nome)

        val res = db.insert("usuarios", null, contentValues)
        db.close()

        listaUsuarios.add(Usuarios(res.toInt(), nome))

        return res
    }

    fun updateNome(id: Int, nome: String) : Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put("nome", nome)
        val res = db.update("usuarios", contentValues, "id=?", arrayOf(id.toString()))
        db.close()

        return res
    }

    fun deleteNome(id: Int) : Int{
        val db = this.writableDatabase
        val res = db.delete("usuarios", "id=?", arrayOf(id.toString()))
        db.close()

        return res
    }

    fun usuariosSelectAll() : ArrayList<Usuarios> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM usuarios", null)
        val listaNome : ArrayList<Usuarios> = ArrayList()

        if (c.count > 0) {
            c.moveToFirst()
            do {
                val idIndex = c.getColumnIndex("id")
                val nomeIndex = c.getColumnIndex("nome")

                val id = c.getInt(idIndex)
                val nome = c.getString(nomeIndex)

                listaNome.add(Usuarios(id, nome))
            } while (c.moveToNext())
        }

        db.close()

        return listaNome
    }

    fun getNomeById(id: Int) : String {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT nome FROM usuarios WHERE id=?", arrayOf(id.toString()))
        var nome: String = ""

        c.moveToFirst()
        if (c.count == 1) {
            val nomeIndex = c.getColumnIndex("nome")
            nome = c.getString(nomeIndex)
        }
        db.close()

        return nome
    }

    fun getIdByNome(nome: String) : Int {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT id FROM usuarios WHERE nome=?", arrayOf(nome))
        var id: Int = -1

        c.moveToFirst()
        if (c.count > 0) {
            val idIndex = c.getColumnIndex("id")
            id = c.getInt(idIndex)
        }
        db.close()

        return id
    }
}