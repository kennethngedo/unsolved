package com.example.unsolved.common.utils

interface Logger {
    fun error(tag: String, message: String)
    fun debug(tag: String, message: String)
    fun info(tag: String, message: String)
    fun verbose(tag: String, message: String)
}