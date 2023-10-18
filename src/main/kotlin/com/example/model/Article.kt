package com.example.model

import com.example.model.database.DbConnection
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Articles : Table<Nothing>("articles") {
  val id = int("art_id").primaryKey()
  val title = varchar("art_title")
  val body = varchar("art_body")
}

data class Article(val id: Int, var title: String, var body: String)
