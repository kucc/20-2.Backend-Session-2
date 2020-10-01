package com.todo.demo.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
@Table(name="user_has_category")
public class UserCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="level", columnDefinition = "TINYINT")
    private int level;

    @Setter
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Setter
    @ManyToOne
    @JoinColumn(name="catetory_id")
    private Category category;

    @Builder
    public UserCategory(int level, User user, Category category){
        this.level = level;
        this.user = user;
        this.category = category;
    }
}
