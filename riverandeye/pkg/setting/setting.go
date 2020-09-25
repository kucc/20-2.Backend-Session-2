package setting

import (
	"log"
	"time"

	"gopkg.in/ini.v1"
)

// Database Setting type
type Database struct {
	Type        string
	User        string
	Password    string
	Host        string
	Name        string
	TablePrefix string
}

// DatabaseSetting is setting for database
var DatabaseSetting = &Database{}

// Server Setting Type
type Server struct {
	RunMode      string
	HTTPPort     int
	ReadTimeout  time.Duration
	WriteTimeout time.Duration
}

// ServerSetting Object
var ServerSetting = &Server{}

var cfg *ini.File

// Setup is for initialize settings
func Setup() {
	var err error
	cfg, err = ini.LoadSources(ini.LoadOptions{UnescapeValueCommentSymbols: false}, "conf/app.ini")
	if err != nil {
		log.Fatalf("setting.Setup, fail to parse 'conf/app.ini': %v", err)
	}

	mapTo("database", DatabaseSetting)
	mapTo("server", ServerSetting)

	ServerSetting.ReadTimeout = ServerSetting.ReadTimeout * time.Second
	ServerSetting.WriteTimeout = ServerSetting.WriteTimeout * time.Second
}

func mapTo(section string, v interface{}) {
	err := cfg.Section(section).MapTo(v)
	if err != nil {
		log.Fatalf("Cfg.MapTo %s err: %v", section, err)
	}
}
