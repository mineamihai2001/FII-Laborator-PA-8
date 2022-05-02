package application;

import java.sql.SQLException;

public class Continent extends BaseModel {
    DataBase db;
    private final String[] CONTINENTS = {"Europe", "Asia", "Australia", "Antarctica", "South-America", "North-America", "Africa"};

    public Continent() {
        db = DataBase.getInstance();
    }

    public void createContinent(int id, String name) throws SQLException {
        String sql = String.format("INSERT INTO CONTINENTS (id, name)" +
                "VALUES(%d, '%s')", id, name);
        System.out.println(sql);
        this.db.query(sql);
    }

    public void populateContinents() throws SQLException {
        for(int i = 0; i<CONTINENTS.length; ++i) {
            createContinent(i+1, CONTINENTS[i]);
        }
    }

}
