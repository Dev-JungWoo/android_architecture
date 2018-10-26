package com.vincent.usecases.base

interface Interactor<T> {
    fun execute(): T
}