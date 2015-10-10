/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure;

import entity.User;
import java.util.List;
import rest.exception.UserAlreadyExistsException;

/**
 *
 * @author Alexander
 */
public interface IUserFacade {

    User getUser(int id);

    List<User> getUsers();
    
    User addUser(User user) throws UserAlreadyExistsException;
}
