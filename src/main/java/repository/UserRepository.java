package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import model.User;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    @Transactional
    public User create(User user) {
        persist(user);
        return user;
    }

    public List<User> listAll() {
        return findAll(Sort.by("fullName")).list();
    }

    public User findById(Long id) {
        User user = find("id", id).firstResult();
        if (user == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        return user;
    }

    @Transactional
    public User update(Long id, User updatedUser) {
        User user = findById(id);
        user.setCpf(updatedUser.getCpf());
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setSpecialization(updatedUser.getSpecialization());
        user.setInstitution(updatedUser.getInstitution());
        return user;
    }

    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            delete(user);
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }
}