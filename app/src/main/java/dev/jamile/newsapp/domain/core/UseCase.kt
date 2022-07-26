package dev.jamile.newsapp.domain.core

import dev.jamile.newsapp.network.ResponseError

interface UseCase<S> {

    suspend fun execute(
        parameters: ParametersDTO = ParametersDTO{},
        onSuccess: (S) -> Unit,
        onError: (ResponseError) -> Unit
    )

}