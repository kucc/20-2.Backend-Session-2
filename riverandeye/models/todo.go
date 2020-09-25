package models

import "github.com/jinzhu/gorm"

// Todo : Database Table about Todo
type Todo struct {
	gorm.Model
	ID         int    `gorm:"primaryKey;autoIncrement" json:"id"`
	Content    string `json:"content"`
	Finished   bool   `json:"finished"`
	FinishedAt string `json:"finished_at"`
	CreatedAt  string `json:"created_at"`
	CategoryID int    `json:"category_id"`
}

// CreateTodo creates todo into database
func CreateTodo(data map[string]interface{}) error {
	todo := Todo{
		Content:    data["content"].(string),
		Finished:   false,
		FinishedAt: "",
		CreatedAt:  "",
		CategoryID: data["categoryId"].(int),
	}

	if err := db.Create(&todo).Error; err != nil {
		return err
	}

	return nil
}
