package tk.monkeycode.blogapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tk.monkeycode.blogapi.dto.ArticleRequest;
import tk.monkeycode.blogapi.dto.ArticleResponseDTO;
import tk.monkeycode.blogapi.dto.CommentResponseDTO;
import tk.monkeycode.blogapi.exception.ModelAlreadyExistException;
import tk.monkeycode.blogapi.exception.ModelNotFoundException;
import tk.monkeycode.blogapi.model.Article;
import tk.monkeycode.blogapi.model.Comment;
import tk.monkeycode.blogapi.model.User;
import tk.monkeycode.blogapi.repository.ArticleRepository;
import tk.monkeycode.blogapi.repository.CommentRepository;
import tk.monkeycode.blogapi.repository.UserRepository;
import tk.monkeycode.blogapi.service.ArticleService;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	
	private final ArticleRepository articleRepository;
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;

	@Override
	public List<ArticleResponseDTO> findArticles(String tag, String author, String favorited, String limit, String offset) {
		List<Article> articles = articleRepository.findAll();
		return articles.stream().map(ArticleResponseDTO::new).toList();
	}

	@Override
	public List<ArticleResponseDTO> findArticlesFeed(String limit, String offset) {
		// TODO Auto-generated method stub
		return new ArrayList<ArticleResponseDTO>();
	}

	@Override
	@Transactional
	public ArticleResponseDTO findArticleBySlug(String slug) {
		Article article = articleRepository.findArticleBySlug(slug).orElseThrow(() -> new ModelNotFoundException("Artículo no existe."));
		return new ArticleResponseDTO(article);
	}

	@Override
	@Transactional
	public ArticleResponseDTO createArticle(ArticleRequest article) {
		var principal = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new ModelNotFoundException("Usuario no existe."));
		String slug = article.getArticle().getTitle().toLowerCase().replace(" ", "-");
		Optional<Article> foundArticle = articleRepository.findArticleBySlug(slug);
		foundArticle.ifPresent((a) -> {throw new ModelAlreadyExistException("Ya existe un artículo con slug " + slug);});
		Article newArticle = new Article(article.getArticle());
		newArticle.setAuthor(currentUser.getProfile());
		Article savedArticle = articleRepository.save(newArticle);
		return new ArticleResponseDTO(savedArticle);
	}

	@Override
	@Transactional
	public ArticleResponseDTO updateArticle(String slug, ArticleRequest article) {
		Article savedArticle = articleRepository.findArticleBySlug(slug).orElseThrow(() -> new ModelNotFoundException("Artículo no existe."));
		savedArticle.setTitle(article.getArticle().getTitle());
		savedArticle.setDescription(article.getArticle().getDescription());
		savedArticle.setBody(article.getArticle().getBody());
		return new ArticleResponseDTO(savedArticle);
	}

	@Override
	@Transactional
	public void deleteArticle(String slug) {
		Article article = articleRepository.findArticleBySlug(slug).orElseThrow(() -> new ModelNotFoundException("Artículo no existe."));
		articleRepository.delete(article);
	}

	@Override
	public ArticleResponseDTO favoriteArticle(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleResponseDTO unfavoriteArticle(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public CommentResponseDTO addComment(String slug, String body) {
		Article article = articleRepository.findArticleBySlug(slug).orElseThrow(() -> new ModelNotFoundException("Artículo no existe."));
		var principal = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new ModelNotFoundException("Usuario no existe."));
		Comment comment = new Comment();
		comment.setArticle(article);
		comment.setAuthor(currentUser.getProfile());
		comment.setBody(body);
		article.addComment(comment);
		Article updatedArticle = articleRepository.save(article);
		return new CommentResponseDTO(updatedArticle.getComments().get(0));
	}

	@Override
	@Transactional
	public List<CommentResponseDTO> getArticleComments(String slug) {
		Article article = articleRepository.findArticleBySlug(slug).orElseThrow(() -> new ModelNotFoundException("Artículo no existe."));
		List<Comment> comments = article.getComments();
		return comments.stream().map(CommentResponseDTO::new).toList();
	}

	@Override
	@Transactional
	public void deleteComments(String slug, Long id) {
		Article article = articleRepository.findArticleBySlug(slug).orElseThrow(() -> new ModelNotFoundException("Artículo no existe."));
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Commentario no existe."));
		article.removeComment(comment);
	}

}
