package generic


// 긴 타입을 typealias을 통해 변수로 지정하고 사용할 수 있음.
typealias mapStore = Map<ConstraintsGenericCage<Bird>, ProducerGenericsCage<Animal>>

fun handleCacheStore(store: mapStore) {}
