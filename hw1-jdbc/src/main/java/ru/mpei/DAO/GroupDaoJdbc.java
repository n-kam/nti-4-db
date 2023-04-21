package ru.mpei.DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.Domain.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class GroupDaoJdbc implements GroupDao {

    private final NamedParameterJdbcOperations jdbc;

    public GroupDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Group g) {
        jdbc.update("insert into groups (id, name, g_year) values (:id, :n, :y)",
                Map.of("id", g.getId(), "n", g.getName(), "y", g.getYear()));
    }

    @Override
    public Group getById(long id) {
        return jdbc.queryForObject("select * from groups where id=:id", Map.of("id", id), new GroupMapper());
    }

    @Override
    public List<Group> getAll() {
        return jdbc.query("select * from groups", new GroupMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from groups where id=:id", Map.of("id", id));
    }

    private static class GroupMapper implements RowMapper<Group> {

        @Override
        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Group(rs.getLong("id"),
                    rs.getString("name"),
                    rs.getLong("g_year"),
                    new ArrayList<>());
        }
    }
}
