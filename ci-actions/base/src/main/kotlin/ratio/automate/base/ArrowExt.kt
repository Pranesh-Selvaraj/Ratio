package ratio.automate.base

import arrow.core.Either

fun <A, B> Either<A, B>.getOrThrow(): B {
    return fold(
        ifLeft = { throw RatioError(it.toString()) },
        ifRight = { it }
    )
}
