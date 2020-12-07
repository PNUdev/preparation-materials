import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemMapper {

    public List<Item> mapList(ResultSet resultSet) {

        try (resultSet) {
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {

                Item item = map(resultSet);
                items.add(item);
            }

            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Item> mapItem(ResultSet resultSet) {

        try (resultSet) {
            if (resultSet.next()) {
                return Optional.of(map(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Item map(ResultSet resultSet) throws SQLException {
        return Item.builder() // ORM -> Object-relational mapping
                .id(resultSet.getLong("id"))
                .content(resultSet.getString("content"))
                .done(resultSet.getBoolean("done"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }

}
