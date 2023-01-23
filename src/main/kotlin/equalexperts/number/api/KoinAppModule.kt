package equalexperts.number.api

import equalexperts.number.api.services.NumberService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::NumberService) {
        bind()
        createdAtStart()
    }
}
