package distributedsystems.a1.services;

import distributedsystems.a1.DTO.UserDTO;
import distributedsystems.a1.entities.User;
import distributedsystems.a1.repositories.UserRepo;

import java.util.List;

public interface UserService {

    public List<UserDTO> findUsers();
    public UserDTO findPersonById(Long id);
    public Long insertUser(UserDTO userDTO);
    public Long updateUser(Long user_id, UserDTO userDTO);
    public void deleteUser(Long userID);

}
