package com.example.webApplication.userpost;

import com.example.webApplication.appuser.AppUser;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "user_post")
@AllArgsConstructor
@NoArgsConstructor
/* Post class, with lombok annotation for the getters, setters and some of the constructors,
 * It is to be stored in a database as an entity.
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
    @ManyToOne(cascade = CascadeType.ALL)
    private AppUser appUser;
    private ArrayList<String> comments;
    private int likes;

    /* Constructor for the users' post that does not contain the id and comments, since the
    comments will be asked with a getter, and the id is to be generated by sequence generator.
     */
    public UserPost(AppUser appUser, String title, String content, int likes) {
        this.appUser = appUser;
        this.title = title;
        if(title.length() < 16 || title.length() > 64) {
            throw new IndexOutOfBoundsException("Title must contain between 16 and 64 characters!");
        }
        this.content = content;
        if(content.length() > 8192) {
            throw new IndexOutOfBoundsException("Content section should not exceed 8192 characters!");
        }
        this.likes = likes;
    }
}
