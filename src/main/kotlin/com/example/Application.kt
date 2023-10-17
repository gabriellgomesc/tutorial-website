package com.example

import com.example.plugins.*
import configureTemplating
import io.ktor.server.application.*

fun main(args: Array<String>) {
  io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
  configureTemplating()
  configureRouting()
}

// KTORM