package com.todo.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserHasCategoryKey implements Serializable {
    @Column(name="user_id")
    private Integer userId;

    @Column(name="category_id")
    private Integer categoryId;
}
