package com.chansu.webservice.service;

import com.chansu.webservice.domain.reply.Reply;
import com.chansu.webservice.domain.reply.ReplyRepository;
import com.chansu.webservice.dto.posts.PostsMainResponseDto;
import com.chansu.webservice.dto.posts.PostsModifyRequestDto;
import com.chansu.webservice.dto.posts.PostsSaveRequestDto;
import com.chansu.webservice.dto.reply.ReplyMainResponseDTO;
import com.chansu.webservice.dto.reply.ReplyModifyRequestDto;
import com.chansu.webservice.dto.reply.ReplySaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ReplyService {
    ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    public List<ReplyMainResponseDTO> getReplylist(Long postNo){
        List<Reply> list = replyRepository.findByPostNo(postNo);
        List<ReplyMainResponseDTO> replyDtoList = new ArrayList<>();
        if(list.isEmpty()){
            return replyDtoList;
        }

        for(Reply reply : list){
            replyDtoList.add(new ReplyMainResponseDTO(reply));
        }
        return replyDtoList;
    }

    @Transactional
    public Long save(ReplySaveRequestDto dto){
        return replyRepository.save(dto.toEntity()).getReplyNo();
    }

    @Transactional
    public Long modify(ReplyModifyRequestDto dto){
        if(replyRepository.existsById(dto.getReplyNo())){
            replyRepository.modify(dto.getReplyContent(), dto.getReplyWriter(), dto.getReplyNo());
        }
        return dto.getReplyNo();
    }

    @Transactional
    public Long delete(Long replyNo){
        if(replyRepository.existsById(replyNo)){
            replyRepository.deleteById(replyNo);
        }
        return replyNo;
    }
}
