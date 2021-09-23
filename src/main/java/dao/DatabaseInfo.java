package dao;


/**
 * Information needed to connect to the Project 1 database.
 */
public interface DatabaseInfo {
    String DATABASE_URL = System.getenv("DATABASE_URL");
    String DATABASE_USERNAME = System.getenv("DATABASE_USERNAME");
    String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");
}
