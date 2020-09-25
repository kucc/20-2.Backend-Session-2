package main

import (
	"fmt"
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/kucc/20-2.Backend-Session-2/riverandeye/models"
	"github.com/kucc/20-2.Backend-Session-2/riverandeye/pkg/setting"
	"github.com/kucc/20-2.Backend-Session-2/riverandeye/routers"
)

func init() {
	setting.Setup()
	models.Setup()
}

func main() {
	gin.SetMode(setting.ServerSetting.RunMode)

	route := routers.InitRouter()
	readTimeout := setting.ServerSetting.ReadTimeout
	writeTimeout := setting.ServerSetting.WriteTimeout
	endPoint := fmt.Sprintf(":%d", setting.ServerSetting.HTTPPort)
	maxHeaderBytes := 1 << 20

	server := &http.Server{
		Addr:           endPoint,
		Handler:        route,
		ReadTimeout:    readTimeout,
		WriteTimeout:   writeTimeout,
		MaxHeaderBytes: maxHeaderBytes,
	}

	log.Printf("[info] start http server listening %s", endPoint)

	server.ListenAndServe()
}
