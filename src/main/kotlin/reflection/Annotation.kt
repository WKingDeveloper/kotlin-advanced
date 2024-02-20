package reflection

/**
 * 명시적으로 지정해주지 않는 경우 @Circle의 어노테이션 적용은
 * 생성자 parameter > 프로퍼티 > 필드 이다. 하지만 @Circle의 경우 Field에만 적용 가능하기 때문에
 * name field에 어노테이션이 적용되게 된다.
 * 명시적으로 지정하고 싶은 경우 @get:Circle 처럼 적용해주면 된다. (use-site target 사용)
 */
@Shape(texts = ["A", "B"], Shape::class)
@Shape(texts = ["A", "B"], Shape::class)
class Annotation(@Circle val name: String) {
}

annotation class Bean
