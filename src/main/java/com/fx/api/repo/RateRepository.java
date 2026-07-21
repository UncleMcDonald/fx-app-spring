package com.fx.api.repo;

import com.fx.api.model.Rate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RateRepository {

    private static final RowMapper<Rate> MAPPER = (rs, rowNum) -> new Rate(
            rs.getInt("id"),
            rs.getString("base_code"),
            rs.getString("quote_code"),
            rs.getDouble("rate"),
            rs.getDate("rate_date").toLocalDate()
    );

    private final JdbcTemplate jdbc;

    public RateRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Rate> findLatest() {
        String sql = """
                SELECT r.*
                FROM fx_rate r
                WHERE r.rate_date = (SELECT MAX(rate_date) FROM fx_rate)
                ORDER BY r.base_code, r.quote_code
                """;
        return jdbc.query(sql, MAPPER);
    }

    public Optional<Rate> findLatestForPair(String base, String quote) {
        String sql = """
                SELECT *
                FROM fx_rate
                WHERE base_code = ? AND quote_code = ?
                ORDER BY rate_date DESC
                LIMIT 1
                """;
        return jdbc.query(sql, MAPPER, base, quote).stream().findFirst();
    }
}
