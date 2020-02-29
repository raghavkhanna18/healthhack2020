data class Article(
    val id: Int,
    val headline: String,
    val yesInf: Double,
    val noInf: Double,
    var yesEco: Double,
    var noEco: Double,
    val isFake: Boolean = false,
    val actionMessage: String,
    val longTerm : Boolean = false
) {
    init {
        yesEco *= -1
        noEco *= -1
    }
}