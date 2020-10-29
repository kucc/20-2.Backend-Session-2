package com.todo.demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.demo.model.Category;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.todo.demo.model.QCategory.category;
import static com.todo.demo.model.QUserHasCategory.userHasCategory;

@Repository
public class CategoryRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public CategoryRepositorySupport(JPAQueryFactory queryFactory) {
        super(Category.class);
        this.queryFactory = queryFactory;
    }

    public List<Category> findCategoriesByUserId(int userId){
        return queryFactory.selectFrom(category)
                .innerJoin(category.users, userHasCategory)
                .on(userHasCategory.user.id.eq(userId))
                .fetch();
    }

}
