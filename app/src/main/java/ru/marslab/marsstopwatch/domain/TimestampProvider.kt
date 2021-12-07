package ru.marslab.marsstopwatch.domain

interface TimestampProvider {
    fun getMilliseconds(): Long
}