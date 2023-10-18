package com.example.model.database

import org.ktorm.database.Database
import java.sql.SQLException

object DbConnection {
  private var database: Database? = null

  fun connect(): Database? {
    try {
      val url = "jdbc:postgresql://localhost:5432/tutorial-website"
      val user = "postgres"
      val password = "123456"

      database = Database.connect(url, user, password)
    } catch (e: SQLException) {
      println("Erro ao conectar ao banco de dados: ${e.message}")
      return null
    }
    return database
  }
}
