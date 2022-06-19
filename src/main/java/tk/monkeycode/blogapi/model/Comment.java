package tk.monkeycode.blogapi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



import lombok.Data;

@Data
@Entity
@Table(name="comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String body;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comments_articles"))
	private Article article;
	
	@ManyToOne
	@JoinColumn(name = "profile_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comments_profile"))
	private Profile author;
	
}
