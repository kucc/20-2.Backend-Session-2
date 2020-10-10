package com.todo.demo.dto;

import com.todo.demo.model.Category;
import com.todo.demo.model.ToDo;
import com.todo.demo.model.UserHasCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

public class CategoryDto {

    @Getter
    @Setter
    public static class Request{
        private String title;

        public Category toEntity(){
            return Category.builder()
                    .title(title)
                    .build();
        }
    }

    public static class ListResponse{
        private Integer num;
        private List<CategoryDto.Response> categoryList;
    }

    @Getter
    @Setter
    public static class Response{
        private Integer id;
        private String title;
        private List<ToDo> toDoList;

        public Response(Category category){
            this.id = category.getId();
            this.title = category.getTitle();
            this.toDoList = category.getToDoList();
        }
    }
}
