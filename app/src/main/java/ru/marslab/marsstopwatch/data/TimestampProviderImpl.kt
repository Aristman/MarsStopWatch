package ru.marslab.marsstopwatch.data

import ru.marslab.marsstopwatch.domain.TimestampProvider

class TimestampProviderImpl : TimestampProvider {
    override fun getMilliseconds(): Long =
        System.currentTimeMillis()

}