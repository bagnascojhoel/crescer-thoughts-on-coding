package br.com.cwi.crescer.tcc.jhoel.bagnasco.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@Table(name = "toc_post")
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_to_id")
    private Post commentTo;

    @OneToMany(mappedBy = "commentTo")
    private List<Post> comments;

    @Column(name = "content")
    private String content;

    @Column(name = "creation")
    private LocalDateTime creation;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private PostVisibility visibility;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "toc_like",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likes;

}
