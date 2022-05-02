package application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class City {
    private DataBase db;

    public City() {
        db = DataBase.getInstance();
    }

    public void createCity(int id, String country, String name, float latitude, float longitude) throws SQLException {
        String sql = String.format("INSERT INTO CITIES (id, country, name, capital, latitude, longitude)" +
                "VALUES (%d, '%s', '%s', %d, %f, %f)", id, country, name, 1, latitude, longitude);
        System.out.println(sql);
        this.db.query(sql);
    }

    public double getDistance(String city1, String city2) throws SQLException {
        String sql = String.format("SELECT latitude, longitude FROM cities " +
                "WHERE name = ' %s'", city1);
        String sql2 = String.format("SELECT latitude, longitude FROM CITIES " +
                "WHERE name='%s'", city2);
        ResultSet rs = this.db.query(sql);
        ResultSet rs2 = this.db.query(sql2);
        float lat1 = 0, lat2 = 0, long1 = 0, long2 = 0;
        while (rs.next()) {
            lat1 = rs.getFloat("latitude");
            long1 = rs.getFloat("longitude");
        }
        while (rs2.next()) {
            lat2 = rs.getFloat("latitude");
            long2 = rs.getFloat("longitude");
        }
        double dlong = Math.toRadians(long2) - Math.toRadians(long1);
        double dlat = Math.toRadians(lat2) - Math.toRadians(lat1);
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlong / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;

        rs.close();
        System.out.println("The distance between " + city1 + " and " + city2 + " is: " + c * r + " km");
        return(c * r);
    }
}
