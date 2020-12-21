package persistence;

import domain.MarioException;
import domain.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOrderMapper {


    private Database database;

    public DbOrderMapper(Database database) {
        this.database = database;
    }

    public Order insertOrder(Order order) throws MarioException {

        boolean result = false;
        int newId = 0;
        String sql = "insert into mario.order (pizza_no, amount, customer_name, customer_phone, pickup_time) values (?,?,?,?,?)";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getPizza_no());
                ps.setInt(2, order.getAmount());
                ps.setString(3, order.getCustomer_name());
                ps.setString(4, order.getCustomer_phone());
                ps.setInt(5, order.getPickup_time());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newId = idResultset.getInt(1);
                    order.setOrder_id(newId);
                } else {
                    order = null;
                }
            } catch (SQLException throwables) {
                throw new MarioException("kære Mario, der er sket en databasefejl");
            }
        } catch (MarioException | SQLException e ) {
            throw new MarioException("kære Mario, der er sket en databasefejl");
        }
        return order;
    }

    public List<Order> getAllOrders() throws MarioException {

        List<Order> orderList = new ArrayList<>();

        String sql = "select * from mario.order";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    int pizza_no = rs.getInt("pizza_no");
                    int amount = rs.getInt("amount");
                    String customer_name = rs.getString("customer_name");
                    String customer_phone = rs.getString("customer_phone");
                    int pickup_time = rs.getInt("pickup_time");
                    orderList.add(new Order(pizza_no, amount, customer_name, customer_phone, pickup_time));
                }
            } catch (SQLException throwables) {
                throw new MarioException("kære Mario, der er sket en databasefejl");
            }
        } catch (MarioException | SQLException e ) {
            throw new MarioException("kære Mario, der er sket en databasefejl");
        }
        return orderList;
    }

    public boolean deleteOrder(int order_id) throws MarioException {
        boolean result = false;
        String sql = "delete from mario.order where order_id = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,order_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1){
                    result = true;
                }
            } catch (SQLException throwables) {
                throw new MarioException("kære Mario, der er sket en databasefejl");
            }
        } catch (MarioException | SQLException e ) {
            throw new MarioException("kære Mario, der er sket en databasefejl");
        }
        return result;
    }

    public boolean updateOrder(Order order) throws MarioException {
        boolean result = false;
        String sql = "update mario.order set order_id = ?, pizza_no = ?, amount = ?, customer_name = ?, customer_phone = ?, pickup_time = ? where order_id = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order.getOrder_id());
                ps.setInt(2, order.getPizza_no());
                ps.setInt(3, order.getAmount());
                ps.setString(4, order.getCustomer_name());
                ps.setString(5, order.getCustomer_phone());
                ps.setInt(6, order.getPickup_time());
                ps.setInt(7, order.getOrder_id());
                int rowsAffected = ps.executeUpdate();
                System.out.println(rowsAffected + " antal linjer ændret");
                if (rowsAffected == 1){
                    result = true;
                }
            } catch (SQLException throwables) {
                throw new MarioException("kære Mario, der er sket en databasefejl");
            }
        } catch (MarioException | SQLException e ) {
            throw new MarioException("kære Mario, der er sket en databasefejl");
        }
        return result;
    }

    public Order getOrderById(int order_id) throws MarioException {
        Order order = null;
        String sql = "select * from mario.order where order_id = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int pizza_no = rs.getInt("pizza_no");
                    int amount = rs.getInt("amount");
                    String customer_name = rs.getString("customer_name");
                    String customer_phone = rs.getString("customer_phone");
                    int pickup_time = rs.getInt("pickup_time");
                    order = new Order(pizza_no, amount, customer_name, customer_phone, pickup_time);
                }
            } catch (SQLException throwables) {
                throw new MarioException("kære Mario, der er sket en databasefejl");
            }
        } catch (MarioException | SQLException e ) {
            throw new MarioException("kære Mario, der er sket en databasefejl");
        }
        return order;
    }

    public List<Order> getAllOrdersSortByPickupTime() throws MarioException {

        List<Order> orderList = new ArrayList<>();

        String sql = "select * from mario.order order by pickup_time";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    int pizza_no = rs.getInt("pizza_no");
                    int amount = rs.getInt("amount");
                    String customer_name = rs.getString("customer_name");
                    String customer_phone = rs.getString("customer_phone");
                    int pickup_time = rs.getInt("pickup_time");
                    orderList.add(new Order(pizza_no, amount, customer_name, customer_phone, pickup_time));
                }
            } catch (SQLException throwables) {
                throw new MarioException("kære Mario, der er sket en databasefejl");
            }
        } catch (MarioException | SQLException e ) {
            throw new MarioException("kære Mario, der er sket en databasefejl");
        }
        return orderList;
    }

}
