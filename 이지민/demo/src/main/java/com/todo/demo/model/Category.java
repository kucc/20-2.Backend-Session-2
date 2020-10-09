package com.todo.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString
@Table(name="category")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column(length=100)
    private String title;

    @Setter
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ToDo> toDos;

    @Setter
    @OneToMany(mappedBy="category")
    private Set<UserHasCategory> users;

    public void addToDo(ToDo t){
        List<ToDo> toDos = getToDos();
        toDos.add(t);
    }

    @Builder
    public Category(String title){
        this.title = title;
    }
}
