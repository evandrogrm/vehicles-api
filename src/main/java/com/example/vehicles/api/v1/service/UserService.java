package com.example.vehicles.api.v1.service;

import com.example.vehicles.api.v1.controller.filter.UserFilter;
import com.example.vehicles.api.v1.repository.UserRepositoryV1;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.exception.UserNotFoundException;
import com.example.vehicles.api.v1.service.exception.UserWithEmailAlreadyExistsException;
import com.example.vehicles.api.v1.service.vo.UserVO;
import com.example.vehicles.domain.User;
import com.example.vehicles.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepositoryV1 repository;

    @Autowired
    private UserMapper mapper;

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public UserVO create(UserVO requestVO) throws AbstractException {
        User userWithSameEmail = repository.findByEmail(requestVO.getEmail());
        if (userWithSameEmail != null) {
            throw new UserWithEmailAlreadyExistsException(requestVO.getEmail());
        }

        User user = new User()
                .setId(UUID.randomUUID().toString())
                .setName(requestVO.getName())
                .setEmail(requestVO.getEmail());

        user = repository.save(user);

        return mapper.map(user, UserVO.class);
    }

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public UserVO update(UserVO requestVO) throws AbstractException {
        User user = findEntityById(requestVO.getId())
                .setName(requestVO.getName())
                .setEmail(requestVO.getEmail());

        user = repository.save(user);

        return mapper.map(user, UserVO.class);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Page<UserVO> search(UserFilter userFilter, Pageable pageable) {
        Page<User> users = repository.findAll(userFilter, pageable);
        return mapper.toUserResponseVOPage(users);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public UserVO findById(String id) throws AbstractException {
        User user = findEntityById(id);
        return mapper.map(user, UserVO.class);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public User findEntityById(String id) throws AbstractException {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public void deleteById(String id) throws AbstractException {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        repository.delete(user);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email));
    }
}
