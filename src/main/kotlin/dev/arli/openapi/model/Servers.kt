package dev.arli.openapi.model

import io.ktor.http.Url

typealias ServersBuilder = Servers.Builder.() -> Unit

data class Servers(
    private val servers: List<Server> = emptyList()
) : List<Server> by servers {

    class Builder {
        val servers: MutableList<Server> = mutableListOf()

        inline fun server(url: Url, builder: ServerBuilder = {}) {
            servers += Server.Builder(url = url).apply(builder).build()
        }

        fun build(): Servers {
            return Servers(servers = servers.toList())
        }
    }
}
