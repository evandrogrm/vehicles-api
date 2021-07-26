package com.example.vehicles.mapper;

import com.example.vehicles.api.v1.dto.UserResponseDTO;
import com.example.vehicles.api.v1.service.vo.UserVO;
import com.example.vehicles.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public final class UserMapper extends Mapper {
    public Page<UserResponseDTO> toUsersResponseDTOPage(Page<UserVO> responseVO) {
        return responseVO.map(vo -> map(vo, UserResponseDTO.class));
    }

    public Page<UserVO> toUserResponseVOPage(Page<User> users) {
        return users.map(u -> map(u, UserVO.class));
    }

    public UserVO toUserVO(User user) {
        return map(user, UserVO.class);
    }

    public UserResponseDTO toUserDTO(UserVO user) {
        return map(user, UserResponseDTO.class);
    }
}
