package com.example.simonniko

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "JuegoDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear la tabla de jugadores
        val createTableQuery = """
            CREATE TABLE Jugadores (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                score INTEGER NOT NULL
            );
        """
        db.execSQL(createTableQuery) // Ejecuta el SQL
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
