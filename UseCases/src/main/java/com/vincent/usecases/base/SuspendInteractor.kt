package com.vincent.usecases.base

interface SuspendInteractor<T> {
    suspend fun execute(): T
}
