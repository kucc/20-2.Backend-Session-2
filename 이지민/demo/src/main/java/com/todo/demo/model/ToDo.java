package com.todo.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Setter
    @Column(name="content")
    @Type(type="text")
    private String content;

    @Setter
    @Column(name="finished", columnDefinition = "TINYINT")
    private Boolean finished;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Setter
    @Column(name="finished_at")
    @CreationTimestamp
    private LocalDateTime finishedAt;

    @Setter
    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @Builder
    public ToDo(String content, Boolean finished, LocalDateTime createdAt, LocalDateTime finishedAt, Category category){
        this.content = content;
        this.finished = finished;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.category = category;
    }


}
