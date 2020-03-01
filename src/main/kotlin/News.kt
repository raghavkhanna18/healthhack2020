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

    fun getAndRemoveRandomArticle(): Article {
        val article: Article = allArticles.random()
        removeArticle(article)
        return article
    }

    fun isEmpty() : Boolean {
        return allArticles.isEmpty()
    }

}