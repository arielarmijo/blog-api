package tk.monkeycode.blogapi.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.monkeycode.blogapi.model.Article;

@NoArgsConstructor
@Getter @Setter
@Schema(name = "ArticleResponseData", description = "Datos de un artículo devueltos por el servidor")
public class ArticleResponseDTO {
	
	@Schema(description = "Texto descriptivo para usar en la url del artículo", example = "how-to-train-your-dragon")
	private String slug;
	
	@Schema(description = "Título del artículo", example = "How to train your dragon")
	private String title;
	
	@Schema(description = "Breve descripción del artículo", example = "Ever wonder how?")
	private String description;
	
	@Schema(description = "Breve descripción del artículo", example = "Ever wonder how?")
	private String body;
	
	@Schema(description = "Fecha de creación del artículo", example = "2016-02-18T03:22:56.637Z")
	private LocalDateTime createdAt;
	
	@Schema(description = "Fecha de actualización del artículo", example = "2016-02-18T03:48:35.824Z")
	private LocalDateTime updatedAt;
	
	@Schema(description = "Indica si el usuario actual ha guardado este artículo como favorito", example = "false")
	private boolean favorited;
	
	@Schema(description = "Número de veces que este artículo ha sido añadido a favoritos", example = "0")
	private int favoritedCount;
	
	@Schema(description = "Author del artículo")
	private ProfileDTO author;
	
	public ArticleResponseDTO(Article article) {
		this.slug = article.getSlug();
		this.title = article.getTitle();
		this.description = article.getDescription();
		this.body = article.getBody();
		this.createdAt = article.getCreatedAt();
		this.updatedAt = article.getUpdatedAt();
		this.favorited = false;
		this.favoritedCount = 0;
		this.author = new ProfileDTO(article.getAuthor());
	}

}
