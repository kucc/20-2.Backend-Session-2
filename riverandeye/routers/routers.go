package routers

import (
	"github.com/gin-gonic/gin"
	v1 "github.com/kucc/20-2.Backend-Session-2/riverandeye/routers/api/v1"
)

// InitRouter initialize routing information
func InitRouter() *gin.Engine {
	r := gin.New()
	r.Use(gin.Logger())
	r.Use(gin.Recovery())

	apiv1 := r.Group("api/v1")

	apiv1.GET("/user", v1.GetUser)
	apiv1.GET("/todo", v1.GetTodo)
	apiv1.GET("/category", v1.GetCategory)

	return r
}
