package v1

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// GetUser gets user
func GetUser(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{"message": "OK"})
}
