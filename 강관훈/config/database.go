package config

import (
	"fmt"
	"os"
	"strconv"

	"github.com/jinzhu/gorm"
)

// db variable holds ORM Connection
var db *gorm.DB

// DBConfig type is Type for Configuration
type DBConfig struct {
	Host     string
	Port     int
	User     string
	DBName   string
	Password string
}

// GetDB is Getter for ORM Connection
func GetDB() *gorm.DB {
	return db
}

// SetDB is Setter for ORM Connection
func SetDB(conn *gorm.DB) {
	db = conn
}

// BuildDBConfig buils DB Config and return
func BuildDBConfig() *DBConfig {
	port, err := strconv.Atoi(os.Getenv("DB_PORT"))
	if err != nil {
		panic("DB : No Port Specified") // TODO : Panic 하는 게 옳은건거?
	}

	dbConfig := DBConfig{
		Host:     os.Getenv("DB_HOST"),
		Port:     port,
		User:     os.Getenv("DB_USER"),
		DBName:   os.Getenv("DB_NAME"),
		Password: os.Getenv("DB_PW"),
	}
	return &dbConfig
}

// GetDbURL gets db url from db config
func GetDbURL(dbConfig *DBConfig) string {
	return fmt.Sprintf(
		"%s:%s@tcp(%s:%d)/%s?charset=utf8&parseTime=True&loc=Local",
		dbConfig.User,
		dbConfig.Password,
		dbConfig.Host,
		dbConfig.Port,
		dbConfig.DBName,
	)
}
