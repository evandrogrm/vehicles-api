package com.example.vehicles.mapper;

import com.example.vehicles.api.v1.dto.MarkResponseDTO;
import com.example.vehicles.api.v1.service.vo.MarkVO;
import com.example.vehicles.domain.Mark;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public final class MarkMapper extends Mapper {
    public Page<MarkResponseDTO> toMarksResponseDTOPage(Page<MarkVO> responseVO) {
        return responseVO.map(vo -> map(vo, MarkResponseDTO.class));
    }

    public Page<MarkVO> toMarkResponseVOPage(Page<Mark> marks) {
        return marks.map(m -> map(m, MarkVO.class));
    }

    public MarkVO toMarkVO(Mark mark) {
        return map(mark, MarkVO.class);
    }

    public MarkResponseDTO toMarkResponseDTO(MarkVO mark) {
        return map(mark, MarkResponseDTO.class);
    }
}
