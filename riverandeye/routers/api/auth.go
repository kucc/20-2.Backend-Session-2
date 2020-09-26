package api

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/gin-gonic/gin/binding"
)

type auth struct {
	Email    string `binding:"required"`
	Password string `binding:"required"`
}

// Authenticate do authentication
func Authenticate(c *gin.Context) {

	email := c.PostForm("email")
	password := c.PostForm("password")

	a := auth{Email: email, Password: password}

	if err := c.ShouldBindWith(&a, binding.Query); err != nil {
		c.JSON(http.StatusOK, gin.H{"message": "OK"})
	} else {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
	}
}
