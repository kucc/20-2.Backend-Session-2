package v1

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// GetCategory gets category
func GetCategory(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{"message": "OK"})
}
