package application;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BaseModel {
    private final DataBase db;
    private final List<Map<String, String>> data;
    private final String[] HEAD = {"Name", "Capital", "Latitude", "Longitude", "Code", "Continent"};

    public BaseModel() {
        db = DataBase.getInstance();
        data = new ArrayList<>();
    }

    public void createTables() throws SQLException {
        db.query("CREATE TABLE countries " +
                "(id integer primary key, name varchar(20), code integer, continent varchar(20))");
        db.query("CREATE TABLE continents" +
                "(id integer primary key, name varchar(20))");
    }

    public List<Map<String, String>> getData() {
        try {
            FileReader fileReader = new FileReader("src/main/java/data/concap.csv");
            CSVReader csvReader = new CSVReader(fileReader);
            String line = null;
            int k = 0;
            boolean isFirst = true;
            while ((line = Arrays.toString(csvReader.readNext())) != null && k < 100) {
                String[] temp = line.split(",");
                Map<String, String> currCountry = new HashMap<>();
                if(!isFirst) {
                    if(temp.length == 6) {
                        currCountry.put("name", temp[0]);
                        currCountry.put("capital", temp[1]);
                        currCountry.put("latitude", temp[2]);
                        currCountry.put("longitude", temp[3]);
                        currCountry.put("code", temp[4]);
                        currCountry.put("continent", temp[5]);
                        data.add(currCountry);
                        ++k;
                    }
                } else {
                    isFirst = false;
                }
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void populateDB() throws SQLException {
        getData();
        String[] temp = null;
        int id = 1;
        System.out.println("POPULATING THE DB... (do not exit)");
        for(Map<String, String>country : data) {
            ++id;
            String sql = String.format("INSERT INTO CITIES (id, country, name, capital, latitude, longitude) " +
                    "VALUES (%d, '%s', '%s', %d, %f, %f)", id, country.get("name"), country.get("capital"), 1, Float.parseFloat(country.get("latitude")), Float.parseFloat(country.get("longitude")));
            this.db.query(sql);
        }
    }

}
