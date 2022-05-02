package application;

import java.sql.SQLException;

public class Country extends BaseModel {
    DataBase db;

    public Country() {
        db = DataBase.getInstance();
    }

    public void createCountry(int id, String name, int code, String continent) throws SQLException {
        String sql = String.format("INSERT INTO COUNTRIES (id, name, code, continent)" +
                "VALUES(%d, '%s', %d, '%s')", id, name, code, continent);
        System.out.println(sql);
        this.db.query(sql);
    }

}
