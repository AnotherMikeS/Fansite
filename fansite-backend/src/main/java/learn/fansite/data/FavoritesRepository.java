package learn.fansite.data;

import models.Favorite;

import java.util.List;

public interface FavoritesRepository {

    List<Favorite> findAll();

    Favorite add(Favorite favorite);

    boolean deleteByPosition(int position);
}
