package com.adrc95.usecase

import arrow.core.Either
import com.adrc95.domain.exception.ApiError

interface Invoker {

  fun <Params, Type : Any> execute(useCase: UseCase<Params, Type>,
              params: Params,
              onResult: (Either<ApiError, Type>) -> Unit)
}