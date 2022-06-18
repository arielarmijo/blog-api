package tk.monkeycode.blogapi.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.monkeycode.blogapi.dto.ArticleRequestDTO;

@Entity
@Table(name="articles")
@NoArgsConstructor
@Getter @Setter
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String slug;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Lob
	private String body;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	private boolean favorited;
	
	@Column(name = "favorited_count")
	private int favoritedCount;
	
	@ManyToOne
	@JoinColumn(name = "profile_id", nullable = false, foreignKey = @ForeignKey(name = "fk_articles_profile"))
	private Profile author;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "article_tag",
               joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
	private Set<Tag> tags;
	
	public Article(ArticleRequestDTO article) {
		this.slug = article.getTitle().toLowerCase().replace(" ", "-");
		this.title = article.getTitle();
		this.description = article.getDescription();
		this.body = article.getBody();
		this.tags = new HashSet<>(article.getTagList().stream().map(Tag::new).toList());
		this.favorited = false;
		this.favoritedCount = 0;
	}
	
}
