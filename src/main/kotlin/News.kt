class News {
    private var allArticles: MutableList<Article> = ArrayList()

    fun addArticle(article: Article) {
        allArticles.add(article)
    }

    fun addArticles(articles: List<Article>) {
        allArticles.addAll(articles)
    }

    fun removeArticle(article: Article) {
        allArticles.remove(article)
    }

    fun removeArticles(articles: List<Article>) {
        allArticles.removeAll(articles)
    }

    fun getArticle(pos: Int): Article {
        return allArticles[pos]
    }

    fun getAndRemoveArticle(pos: Int): Article {
        var article: Article = allArticles[pos]
        removeArticle(article)
        return article
    }

}