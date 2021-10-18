package com.example.examen.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import android.database.sqlite.SQLiteOpenHelper

/**
 * Ing Francisco Garcia Gomez
 * Clase que crea la instancia de la BD local
 */
class BDHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        DB_NAME,
        null,
        DB_VERSION
    ) {

    companion object {
        private const val COMMENTS_TABLE_CREATE =
            "CREATE TABLE bitacora(id INTEGER PRIMARY KEY AUTOINCREMENT, latitud TEXT, longitud TEXT, fecha TEXT)"
        private const val DB_NAME = "bdBitacoraExamen.sqlite"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(COMMENTS_TABLE_CREATE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
    }


}