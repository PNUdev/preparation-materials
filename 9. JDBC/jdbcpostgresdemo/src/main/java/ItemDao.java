import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ItemDao {

    private ItemMapper itemMapper = new ItemMapper();

    private ConnectionProvider connectionProvider = PooledConnectionProvider.getInstance();

    public Item create(Item item) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO item(content, done, created_at)" +
                             " VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS)
        ) {

            LocalDateTime now = LocalDateTime.now();

            statement.setString(1, item.getContent());
            statement.setBoolean(2, item.isDone());
            statement.setTimestamp(3, Timestamp.valueOf(now));

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();

                return item.toBuilder()
                        .id(generatedKeys.getLong("id"))
                        .createdAt(now)
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> findAll() {

        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT id, content, done, created_at FROM item");
            return itemMapper.mapList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Item findById(Long id) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT id, content, done, created_at FROM item WHERE id = ?")
        ) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            return itemMapper.mapItem(resultSet)
                    .orElseThrow(() -> new RuntimeException("Item not found!"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Item update(Item item, Long id) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE item SET content = ?, done = ? WHERE id = ?")
        ) {

            statement.setString(1, item.getContent());
            statement.setBoolean(2, item.isDone());
            statement.setLong(3, id);

            statement.executeUpdate();

            return findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM item  WHERE id = ?")
        ) {

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
