package com.todo.demo.dto;

import com.todo.demo.model.Category;
import com.todo.demo.model.ToDo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{
        private Integer id;
        private String title;
        private List<ToDo> toDoList;

        public static Response of(Category category){
            int id = category.getId();
            String title = category.getTitle();
            List<ToDo> toDoList = category.getToDoList();

            return  new Response(id, title, toDoList);
        }

        public static List<CategoryDto.Response> of(List<Category> categoryList){
            return categoryList.stream()
                    .map(Response::of)
                    .collect(Collectors.toList());
        }
    }
}
