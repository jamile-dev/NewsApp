package dev.jamile.newsapp.util

import dev.jamile.newsapp.common.ProviderContext
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestProviderContext : ProviderContext() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}