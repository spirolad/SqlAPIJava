package com.seizonia.sql.tableCreation;

public enum ColumnType {

    VARCHAR("VARCHAR(255) NULL"),
    INT("INT NULL"),
    TEXT("TEXT NULL"),
    DATE("DATE NULL"),
    TINYINT("TINYINT NULL"),
    SMALLINT("SMALLINT NULL"),
    MEDIUMINT("MEDIUMINT NULL"),
    BIGINT("BIGINT NULL"),
    DECIMAL("DECIMAL NULL"),
    FLOAT("FLOAT NULL"),
    DOUBLE("DOUBLE NULL"),
    REAL("REAL NULL"),
    BIT("BIT NULL"),
    BOOLEAN("BOOLEAN NULL"),
    SERIAL("SERIAL NULL"),
    DATETIME("DATETIME NULL"),
    TIMESTAMP("TIMESTAMP NULL"),
    TIME("TIME NULL"),
    YEAR("YEAR NULL"),
    CHAR("CHAR NULL"),
    TINYTEXT("TINYTEXT NULL"),
    MEDIUMTEXT("MEDIUMTEXT NULL"),
    LONGTEXT("LONGTEXT NULL"),
    BINARY("BINARY NULL"),
    VARBINARY("VARBINARY NULL"),
    TINYBLOB("TINYBLOB NULL"),
    BLOB("BLOB NULL"),
    MEDIUMBLOB("MEDIUMBLOB NULL"),
    LONGBLOB("LONGBLOB NULL"),
    INET6("INET6 NULL"),
    ID("INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(%table%)");

    private String sqlValue;

    ColumnType(String sqlValue){
        this.sqlValue = sqlValue;
    }

    public String getSqlValue() { return sqlValue; }


}
