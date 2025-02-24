package id.my.agungdh.linechat.mapper;

import id.my.agungdh.linechat.dto.LineTokenDTO;
import id.my.agungdh.linechat.entity.LineToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LineTokenMapper {
    LineTokenDTO toLineTokenDTO(LineToken lineToken);
    LineToken toLineToken(LineTokenDTO lineTokenDTO);
}
