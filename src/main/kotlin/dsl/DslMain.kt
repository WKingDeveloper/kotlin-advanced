package dsl

fun main() {
    val yml = dockerCompose {
        version { 3 }
        service(name = "db") {
            image { "mysql" }
            env("USER" - "myuser")
            env("PASSWORD" - "mypassword")
            port(host = 9999, container = 3306)
        }
    }

    println(yml.render("  "))

    val yml2 = dockerCompose {
        service("") {
            /**
             * @DslMarker를 사용하여 계층적으로 가장 가까운곳을 제외하고는 this를 호출되지 못하게 막음.
             * (@DslMarker를 붙이지 않은 경우엔 하위 계층에서도 service 호출 가능
             * 사용하기 위해서는 this@dockerCompose처럼 호출해야함.
             */
            this@dockerCompose.service("") {

            }
        }
    }
}
