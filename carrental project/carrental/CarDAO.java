package carrental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    
    // INSERT CAR INTO DATABASE
    
    public static void insertCar(Car car) throws SQLException {

        String sql = "INSERT INTO cars (register_number, brand, model, year, rental_price) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, car.getRegisterNumber());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getModel());
            ps.setInt(4, car.getYear());
            ps.setDouble(5, car.getRentalPrice());

            ps.executeUpdate();
        }
    }

    
    // GET ALL CARS (VIEW CARS)
    
    public static List<Car> getAllCars() throws SQLException {

        List<Car> cars = new ArrayList<>();

        String sql = "SELECT register_number, brand, model, year, rental_price FROM cars";

        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("register_number"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("rental_price")
                );
                cars.add(car);
            }
        }

        return cars;
    }
}


