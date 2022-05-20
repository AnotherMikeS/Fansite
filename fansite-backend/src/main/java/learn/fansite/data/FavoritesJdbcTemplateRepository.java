package learn.fansite.data;

import learn.fansite.data.mappers.FavoriteMapper;
import models.Favorite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoritesJdbcTemplateRepository implements FavoritesRepository {

    private final JdbcTemplate jdbcTemplate;

    public FavoritesJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Favorite> findAll() {
        final String sql = "select position, name from favorites;";

        return jdbcTemplate.query(sql, new FavoriteMapper());
    }

    @Override
    public Favorite add(Favorite favorite) {
        final String sql = "insert into favorites (position, name) values (?, ?);";

            int rowsAffected = jdbcTemplate.update(sql, favorite.getPosition(), favorite.getName());

        if (rowsAffected <= 0) {
            return null;
        }

        return favorite;
    }

    @Override
    public boolean deleteByPosition(int position) {
        return jdbcTemplate.update("delete from favorites where position = ?", position) > 0;
    }
}
