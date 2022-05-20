package learn.fansite.domain;

import learn.fansite.data.FavoritesRepository;
import models.Favorite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoritesRepository repository;

    public FavoriteService(FavoritesRepository repository) {
        this.repository = repository;
    }

    public List<Favorite> findAll() {
        return repository.findAll();
    }

    public Result<Favorite> add(Favorite favorite) {
        Result<Favorite> result = validate(favorite);
        if (!result.isSuccess()) {
            return result;
        }

        favorite = repository.add(favorite);
        result.setPayload(favorite);
        return result;
    }

    public boolean deleteByPosition(int position) {
        return repository.deleteByPosition(position);
    }

    private Result<Favorite> validate(Favorite favorite) {
        Result<Favorite> result = new Result<>();

        if (favorite == null) {
            result.addMessage("favorite can't be null", ResultType.INVALID);
            return result;
        }

        if (favorite.getPosition() == 0) {
            result.addMessage("position cannot be null", ResultType.INVALID);
        }

        if (favorite.getName() == null) {
            result.addMessage("name cannot be null", ResultType.INVALID);
        } else if (favorite.getName().equals("")) {
            result.addMessage("name cannot be empty", ResultType.INVALID);
        }

        return result;
    }
}


