package com.involves.migratefiletodatabase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MigrateFileToDatabaseApplication

fun main(args: Array<String>) {
    runApplication<MigrateFileToDatabaseApplication>(*args)
}
