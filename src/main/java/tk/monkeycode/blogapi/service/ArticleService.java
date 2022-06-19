package tk.monkeycode.blogapi.service;

import java.util.List;

import tk.monkeycode.blogapi.dto.ArticleRequest;
import tk.monkeycode.blogapi.dto.ArticleResponseDTO;
import tk.monkeycode.blogapi.dto.CommentResponseDTO;

public interface ArticleService {

	List<ArticleResponseDTO> findArticles(String tag, String author, String favorited, String limit, String offset);

	List<ArticleResponseDTO> findArticlesFeed(String limit, String offset);

	ArticleResponseDTO findArticleBySlug(String slug);

	ArticleResponseDTO createArticle(ArticleRequest article);

	ArticleResponseDTO updateArticle(String slug, ArticleRequest article);

	void deleteArticle(String slug);

	ArticleResponseDTO favoriteArticle(String slug);

	ArticleResponseDTO unfavoriteArticle(String slug);

	CommentResponseDTO addComment(String slug, String body);

	List<CommentResponseDTO> getArticleComments(String slug);

	void deleteComments(String slug, Long id);

}
