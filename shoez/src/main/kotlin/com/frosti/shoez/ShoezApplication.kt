package com.frosti.shoez

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShoezApplication

fun main(args: Array<String>) {
	runApplication<ShoezApplication>(*args)
}
