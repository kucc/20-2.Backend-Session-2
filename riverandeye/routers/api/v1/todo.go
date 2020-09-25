package v1

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// GetTodo gets todo data
func GetTodo(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{"message": "OK"})
}
