data class Article(
    val id: Int,
    val headline: String,
    val yesInf: Double,
    val noInf: Double,
    var yesEco: Int,
    var noEco: Int,
    val isFake: Boolean = false,
    val actionMessage: String,
    val transferFactor : Double = 0.0
)