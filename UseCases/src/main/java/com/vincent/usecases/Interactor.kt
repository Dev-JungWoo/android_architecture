package com.vincent.usecases

interface Interactor<T> {
    fun execute(): T
}