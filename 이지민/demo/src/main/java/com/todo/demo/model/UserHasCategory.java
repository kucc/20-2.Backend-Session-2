package com.todo.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_has_category")
 @NoArgsConstructor
public class UserHasCategory {

    @EmbeddedId
    private UserHasCategoryKey id = new UserHasCategoryKey();

    @ManyToOne @Getter
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne @Getter
    @MapsId("categoryId")
    @JoinColumn(name="category_id")
    private Category category;

    @Setter
    @Column(columnDefinition = "TINYINT")
    private boolean level;

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean getLevel(){
        return this.level;
    }

    @Builder
    public UserHasCategory(User user, Category category, boolean level){
        this.user = user;
        this.category = category;
        this.level = level;
    }


}
