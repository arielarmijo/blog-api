package tk.monkeycode.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.monkeycode.blogapi.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	Optional<Article> findArticleBySlug(String slug);
	
}
