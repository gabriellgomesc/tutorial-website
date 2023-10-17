package com.example.plugins

import com.example.routers.articlesRouters
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
  routing {
    static("/static") {
      resources("files")
    }
    get("/") {
      call.respondRedirect("articles")
    }
    route("articles") {
      articlesRouters()
    }
  }
}