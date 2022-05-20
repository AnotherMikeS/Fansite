package learn.fansite.data.mappers;

import models.Favorite;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;

public class FavoriteMapper implements RowMapper<Favorite> {

    @Override
    public Favorite mapRow(ResultSet resultSet, int i) throws SQLException {
        Favorite favorite = new Favorite(resultSet.getInt("position"), resultSet.getString("name"));
        return favorite;
    }
}
