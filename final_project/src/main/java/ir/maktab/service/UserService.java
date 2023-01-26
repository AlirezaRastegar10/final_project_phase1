package ir.maktab.service;

import ir.maktab.entity.User;
import ir.maktab.exceptions.InvalidPasswordException;
import ir.maktab.exceptions.UserNotFoundException;
import ir.maktab.exceptions.WrongUserEmailException;
import ir.maktab.repository.UserRepository;
import ir.maktab.security.SecurityUtils;

import java.util.List;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public User register(User user) {
        return userRepository.create(user);
    }

    public boolean login(String email, String password) throws UserNotFoundException, InvalidPasswordException {
        User user = userRepository.getUserByEmail(email);
        if (user == null)
            throw new UserNotFoundException("user with given email :-- " + email + " -- not found.");

        if (user.getPassword().equals(password)) {
            SecurityUtils.setUser(user);
            return true;
        } else {
            throw new InvalidPasswordException("password is wrong.");
        }
    }

    public void logout() {
        SecurityUtils.setUser(null);
    }

    public User findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void delete(Long id){
        userRepository.delete(id);
    }

    public void changePassword(Long id, String email, String newPassword) throws WrongUserEmailException {
        User user = userRepository.getUserByEmailAndId(id, email);
        if (user != null) {
            user.setPassword(newPassword);
            userRepository.create(user);
        } else {
            throw new WrongUserEmailException("the email entered does not belong to you.");
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
