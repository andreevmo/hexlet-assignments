package exercise.controller;

import exercise.model.Article;
import exercise.model.Category;
import exercise.repository.ArticleRepository;

import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return this.articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        this.articleRepository.deleteById(id);
    }

    // BEGIN
    @PostMapping(path = "")
    public void createArticle(@RequestBody Article article) {
        Category category = categoryRepository.findById(article.getCategory().getId());
        article.setCategory(category);
        articleRepository.save(article);
    }

    @PatchMapping("/{id}")
    public void updateArticle(@RequestBody Article article, @PathVariable(name = "id") long id) {
        Category category = categoryRepository.findById(article.getCategory().getId());
        article.setId(id);
        article.setCategory(category);
        articleRepository.save(article);
    }

    @GetMapping(path = "/{id}")
    public Article getArticle(@PathVariable(name = "id") long id) {
        return articleRepository.findById(id);
    }
    // END
}
