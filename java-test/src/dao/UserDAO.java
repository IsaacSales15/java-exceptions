package src.dao;

import src.exceptions.EmptyStorageException;
import src.exceptions.UserNotFoundException;
import src.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private long nextId = 1;

    private final List<UserModel> models = new ArrayList<UserModel>();

    public UserModel save(final UserModel model) {
        model.setId(nextId++);
        models.add(model);
        return model;
    }

    public void delete(final long id){
        var toDelete = findById(id);
        models.remove(toDelete);
    }

    public UserModel update(final UserModel model){
        var toUpdate = findById(model.getId());
        models.remove(toUpdate);
        models.add(model);
        return model;
    }

    public UserModel findById(final long id){
        verifyStorage();
           return models.stream()
                   .filter(u -> u.getId() == id)
                   .findFirst()
                   .orElseThrow(() -> new UserNotFoundException("User not exists"));
    }

    public List<UserModel> findAll(){
        List<UserModel> result;
        try{
            verifyStorage();
            result = models;

        } catch (EmptyStorageException e){
            e.printStackTrace();
            result = new ArrayList<>();
        }
        return result;
    }

    private void verifyStorage() {
        if(models.isEmpty()) throw new EmptyStorageException("The storage is empty!");
    }
}
