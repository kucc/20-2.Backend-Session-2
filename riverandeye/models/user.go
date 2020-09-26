package models

import (
	"github.com/jinzhu/gorm"
)

// User : Database Table about user who register todo
type User struct {
	gorm.Model
	ID           int        `gorm:"primary_key;autoIncrement" json:"id"`
	Email        string     `json:"email"`
	Password     string     `json:"password"`
	Username     string     `json:"username"`
	ProfileImage string     `json:"profile_image"`
	Categories   []Category `gorm:"many2many:user_has_category"`
}

// CheckUser checks user exists
func CheckUser(email, password string) (bool, error) {
	var user User

	err := db.Select("id").Where(User{Email: email, Password: password}).First(&user).Error
	if err != nil && err != gorm.ErrRecordNotFound {
		return false, err
	}

	if user.ID > 0 {
		return true, nil
	}

	return false, nil
}

// CreateUser Creates User
func CreateUser(email, password, username, profileImage string) error {
	user := User{
		Email:        email,
		Password:     password,
		Username:     username,
		ProfileImage: profileImage,
	}

	if err := db.Create(&user).Error; err != nil {
		return err
	}

	return nil
}
