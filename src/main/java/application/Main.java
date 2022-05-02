package application;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {

        List<Map<String, String>> data;

        BaseModel model = new BaseModel();
//        model.populateDB();
//        Continent continent = new Continent();
//        continent.populateContinents();

        City c = new City();
        c.getDistance("Jerusalem", "Algiers");
        c.getDistance("Brussels", "Ottawa");


    }
}
