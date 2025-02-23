package id.my.agungdh.linechat.mapper;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}
