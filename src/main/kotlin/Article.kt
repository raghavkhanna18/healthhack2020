data class Article(
    val id: Int,
    val headline: String,
    val yesInf: Double,
    val noInf: Double,
    val yesEco: Double,
    val noEco: Double,
    val isFake: Boolean = false,
    val actionMessage: String,
    val longTerm : Boolean = false
) {
}