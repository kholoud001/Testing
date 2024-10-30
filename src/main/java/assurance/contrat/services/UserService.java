package assurance.contrat.services;

import assurance.contrat.model.entities.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User authenticate(String email, String password);

    User getUserById(Long id);

    List<User> getAllUsers();
}
