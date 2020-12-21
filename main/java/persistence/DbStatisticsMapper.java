package persistence;

import domain.MarioException;
import domain.Statistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DbStatisticsMapper {
    private Database database;

    public DbStatisticsMapper(Database database) {
        this.database = database;
    }

    public List<Statistics> statisticsArchived() throws MarioException {

        List<Statistics> statisticsList = new ArrayList<>();
        String sql = "select * from mario.order";

        try (Connection connection = database.connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int pizzaNo = resultSet.getInt("pizza_no");
                int amountSold = resultSet.getInt("amount");
                statisticsList.add(new Statistics(pizzaNo,orderId , amountSold ));
            }
        } catch (MarioException | SQLException e) {
            throw new MarioException("SQL FEJL");
        }
        return statisticsList;
    }



}
