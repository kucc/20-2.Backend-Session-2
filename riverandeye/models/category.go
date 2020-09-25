package models

import "github.com/jinzhu/gorm"

// Category : Database table about Category of Todo
type Category struct {
	gorm.Model
	ID    int    `gorm:"primary_key;autoIncrement" json:"id"`
	Title string `json:"title"`
	Users []User `gorm:"many2many:user_has_category"`
}

// CreateCategory creates category
func CreateCategory(data map[string]interface{}) error {
	category := Category{
		Title: data["title"].(string),
	}

	if err := db.Create(&category).Error; err != nil {
		return err
	}

	return nil
}
