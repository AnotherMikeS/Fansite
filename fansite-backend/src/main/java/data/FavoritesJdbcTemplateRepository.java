package data;

import data.mappers.FavoriteMapper;
import models.Favorite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class FavoritesJdbcTemplateRepository implements FavoritesRepository {

    private final JdbcTemplate jdbcTemplate;

    public FavoritesJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Favorite> findAll() {
        final String sql = "select (position, name) from favorites;";

        return jdbcTemplate.query(sql, new FavoriteMapper());
    }

    @Override
    public Favorite add(Favorite favorite) {
        final String sql = "insert into favorites (position, name) values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, favorite.getPosition());
            ps.setString(2, favorite.getName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        favorite.setPosition(keyHolder.getKey().intValue());
        return favorite;
    }

    @Override
    public boolean deleteByPosition(int position) {
        return jdbcTemplate.update("delete from favorites where position = ?", position) > 0;
    }
}
