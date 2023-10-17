package com.example.routers

import com.example.controllers.ArticleController
import com.example.controllers.articles
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.articlesRouters() {
    get {
      // Show a list of articles
      call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles)))
    }
    get("new") {
      // Show a page with fields for creating a new article
      call.respond(FreeMarkerContent("new.ftl", model = null))
    }
    post {
      // Save an article
      val formParameters = call.receiveParameters()
      val idNewArticle = ArticleController.create(formParameters)
      call.respondRedirect("/articles/${idNewArticle}")
    }
    get("{id}") {
      // Show an article with a specific id
      val id = call.parameters.getOrFail<Int>("id").toInt()
      call.respond(FreeMarkerContent("show.ftl", mapOf("article" to articles.find {it.id == id})))
    }
    get("{id}/edit") {
      // Show a page with fields for editing an article
      val id = call.parameters.getOrFail<Int>("id").toInt()
      call.respond(FreeMarkerContent("edit.ftl", mapOf("article" to articles.find {it.id == id})))
    }
    post("{id}") {
      val id = call.parameters.getOrFail<Int>("id").toInt()
      val formParameters = call.receiveParameters()
      when (formParameters.getOrFail("_action")) {
        "update" -> {
          ArticleController.update(id, formParameters)
          call.respondRedirect("/articles/$id")
        }

        "delete" -> {
          ArticleController.delete(id)
          call.respondRedirect("/articles")
        }
      }
    }
  }