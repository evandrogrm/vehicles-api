package com.example.vehicles.api.v1.service;

import com.example.vehicles.api.v1.dto.LoginRequestDTO;
import com.example.vehicles.api.v1.repository.UserRepositoryV1;
import com.example.vehicles.api.v1.service.vo.LoginVO;
import com.example.vehicles.domain.User;
import com.example.vehicles.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UserRepositoryV1 userRepositoryV1;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginMapper mapper;

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public LoginVO login(LoginRequestDTO requestDTO) {
        //TODO: usar pattern matcher Os dois campos precisam estar preenchidos com no mínimo 3 caracteres, sendo que o email indicado deve ter um formato válido;
        Optional<User> optionalUser = Optional.ofNullable(userRepositoryV1.findByEmail(requestDTO.getEmail()));

        if (!optionalUser.isPresent()) {
            User user = new User()
                    .setId(UUID.randomUUID().toString())
                    .setName(requestDTO.getName())
                    .setEmail(requestDTO.getEmail());
            userRepositoryV1.save(user);
            return generateUserLogin(user);
        }

        User user = optionalUser.get()
                .setName(requestDTO.getName());
        userRepositoryV1.save(user);
        return generateUserLogin(user);
    }

    private LoginVO generateUserLogin(User user) {
        LoginVO loginVO = mapper.map(user, LoginVO.class);
        loginVO.setToken(tokenService.generateTokenUser(user));
        return loginVO;
    }
}
