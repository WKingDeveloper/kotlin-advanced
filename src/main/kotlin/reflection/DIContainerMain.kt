package reflection

import reflection.config.Configuration

fun main() {
    ContainerV1.register(AService::class)
    val aService = ContainerV1.getInstance(AService::class)
    aService.print()

    ContainerV2.register(AService::class)
    ContainerV2.register(BService::class)
    val bService = ContainerV2.getInstance(BService::class)
    bService.print()

    /**
     * 새로운 인스턴스가 아닌 cachedInstances에 저장된 인스턴스가 호출되는지 확인
     */
    ContainerV2.cachedInstances.keys.forEach { println(it.simpleName) }
    ContainerV2.getInstance(BService::class)

    start(DService::class)
    val container3BService = ContainerV3.getInstance(BService::class)
    ContainerV3.getInstance(Configuration::class)
    ContainerV3.getInstance(DService::class)
    ContainerV3.cachedInstances.keys.forEach { println(it.simpleName) }
    container3BService.print()

}

@Bean
class AService {
    fun print() {
        println("A Service 입니다.")
    }
}

@Bean
class BService(
    private val aService: AService,
    private val cService: CService?

) {
    constructor(aService: AService) : this(aService, null)

    fun print() {
        this.aService.print()
    }
}


class CService

