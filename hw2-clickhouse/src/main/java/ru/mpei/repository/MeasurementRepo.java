package ru.mpei.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ru.mpei.domain.Measurement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementRepo {

    private final NamedParameterJdbcTemplate jdbc;


    public void save(Measurement measurement) {
        String sql =
                "insert into hw2.measurements (source,timestamp,value) " +
                        "SETTINGS async_insert=1, wait_for_async_insert=0 " +
                        "values (:source, :timestamp, :value);";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("source", measurement.getSource());
        parameterSource.addValue("timestamp", measurement.getTimestamp());
        parameterSource.addValue("value", measurement.getValue());
        jdbc.update(sql, parameterSource);
    }

    public List<Measurement> findAll() {
        return jdbc.query("select * from hw2.measurements",
                new MeasurementsMapper());
    }


    public static class MeasurementsMapper implements RowMapper<Measurement> {
        @Override
        public Measurement mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Measurement(
                    rs.getTimestamp("timestamp").toInstant(),
                    rs.getNString("source"),
                    rs.getDouble("value")
            );
        }
    }
}
