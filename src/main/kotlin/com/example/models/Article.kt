package com.example.models

import io.ktor.http.*
import io.ktor.server.util.*
import java.util.concurrent.atomic.AtomicInteger

class Article
private constructor(val id: Int, var title: String, var body: String) {
  companion object {
    private val idCounter = AtomicInteger()

    fun newEntry(title: String, body: String) = Article(idCounter.getAndIncrement(), title, body)

    fun create(parameters: Parameters): Int {
      val title = parameters.getOrFail("title")
      val body = parameters.getOrFail("body")
      val newEntry = newEntry(title, body)
      articles.add(newEntry)

      return newEntry.id
    }

    fun update(id: Int, parameters: Parameters) {
      val index = articles.indexOf(articles.find { it.id == id })
      val title = parameters.getOrFail("title")
      val body = parameters.getOrFail("body")
      articles[index].title = title
      articles[index].body = body
    }

    fun delete(id: Int) {
      articles.removeIf { it.id == id }
    }
  }
}

val articles = mutableListOf(Article.newEntry(
  "The drive to develop!",
  "...it's what keeps me going."
))