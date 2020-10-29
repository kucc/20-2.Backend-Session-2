package com.todo.demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.demo.model.Category;
import com.todo.demo.model.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.todo.demo.model.QCategory.category;
import static com.todo.demo.model.QUser.user;
import static com.todo.demo.model.QUserHasCategory.userHasCategory;

import java.util.List;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public UserRepositorySupport(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    public List<User> findUsersByCategoryId(int categoryId){
        return queryFactory.selectFrom(user)
                .innerJoin(user.categories, userHasCategory)
                .on(userHasCategory.category.id.eq(categoryId))
                .fetch();
    }

}
