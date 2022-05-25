package dev.arli.openapi.parser

class PathParametersParser {

    fun parse(path: String): Set<String> {
        return PathParameterRegex.findAll(path).map { it.value }.toSet()
    }

    private companion object {
        val PathParameterRegex = "(?<=\\{).+?(?=})".toRegex()
    }
}
