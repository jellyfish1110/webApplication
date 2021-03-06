package com.example.webApplication.userpost;

import com.example.webApplication.appuser.AppUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
/* Post class, with lombok annotation for the getters, setters and some of the constructors,
 * that is stored in a postgres database as an entity.
  */
public class UserPost {

    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private Long id;
    private String content;
    private String title;
    private LocalDateTime localDateTime;
    private ArrayList<String> comments;
    private Long likes = 0L;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_owner")
    private AppUser owner;


    /* Constructor for the users' post that does not contain the id and comments, since the
    comments will be asked with a getter, and the id is to be generated by sequence generator.
     */
    public UserPost(String title, String content) {
        this.title = title;
        this.content = content;
        this.localDateTime = LocalDateTime.now();
    }
}