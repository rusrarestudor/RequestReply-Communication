package distributedsystems.a1.services;

import distributedsystems.a1.DTO.UserDTO;
import distributedsystems.a1.DTO.builder.UserBuilder;
import distributedsystems.a1.entities.User;
import distributedsystems.a1.repositories.UserRepo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTO> findUsers() {
        List<User> userList = userRepo.findAll();
        return userList.stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findPersonById(Long id) {
        Optional<User> userOptional = userRepo.findById(id);
        if (!userOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toUserDTO(userOptional.get());

    }

    @Override
    public Long insertUser(UserDTO userDTO) {
        User user = UserBuilder.toEntity(userDTO);
        user = userRepo.save(user);
        LOGGER.debug("User with id {} was inserted in db", user.getUserID());
        return user.getUserID();
    }
}
