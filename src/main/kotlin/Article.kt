data class Article(
    val id: Int,
    val headline: String,
    val cost: Int,
    val yesInf: Int,
    val noInf: Int,
    val yesEco: Int,
    val noEco: Int,
    val isFake: Boolean = false,
    val actionMessage: String,
    val longTerm : Boolean = false
) {
    override fun toString(): String {
        return headline
    }
}