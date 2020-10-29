package com.todo.demo.model;

import lombok.*;
import org.modelmapper.internal.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
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
    private List<ToDo> toDoList;

    @Setter
    @OneToMany(mappedBy="category",  cascade = CascadeType.ALL)
    private Set<UserHasCategory> users = new HashSet<UserHasCategory>();


    public void addToDo(ToDo t){
        List<ToDo> toDos = getToDoList();
        toDos.add(t);
    }


    @Builder
    public Category(String title){
        Assert.notNull(title, "title must not be null");
        this.title = title;
    }
}
