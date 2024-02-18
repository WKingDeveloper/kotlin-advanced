package dsl


@YamlDsl
class DockerCompose {
    private var version: Int by onceNotNull()
    private val services = mutableListOf<Service>()

    fun version(init: () -> Int) {
        version = init()
    }

    fun service(name: String, init: Service.() -> Unit) {
        val service = Service(name)
        service.init()
        services.add(service)
    }

    fun render(indent: String): String {
        val builder = StringBuilder()
        builder.appendNew("version: '$version'")
        builder.appendNew("services:")
        builder.appendNew(services.joinToString("\n") { it.render(indent) }.addIndent(indent, 1))
        return builder.toString()
    }

}

fun dockerCompose(init: DockerCompose.() -> Unit): DockerCompose {
    val dockerCompose = DockerCompose()
    dockerCompose.init()
//    init(dockerCompose)
    return dockerCompose
}

