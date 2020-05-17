package com.chansu.webservice.service;

import com.chansu.webservice.domain.posts.Posts;
import com.chansu.webservice.dto.posts.PostInfoResponseDto;
import com.chansu.webservice.dto.posts.PostsModifyRequestDto;
import com.chansu.webservice.dto.posts.PostsSaveRequestDto;
import com.chansu.webservice.domain.posts.PostsRepository;
import com.chansu.webservice.dto.posts.PostsMainResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
//Controller와 Service의 역할 분리
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        //repository 결과로 넘어온 posts를 map을 통해 PostsMainResponseDto로 변환 후 List로 반환
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostInfoResponseDto findPosts(Long id){
        return postsRepository.findById(id).map(PostInfoResponseDto::new).get();
    }

    @Transactional
    public Long modify(PostsModifyRequestDto dto){
        if(postsRepository.existsById(dto.getId())){
            postsRepository.modify(dto.getTitle(), dto.getAuthor(), dto.getContent(), dto.getId());
        }
        return dto.getId();
    }

    @Transactional
    public Long delete(Long id){
        if(postsRepository.existsById(id)){
            postsRepository.deleteById(id);
        }
        return id;
    }

    @Transactional
    public List<PostsMainResponseDto> searchPosts(String keyword) {
        List<Posts> boardEntities = postsRepository.findByTitleContaining(keyword);
        List<PostsMainResponseDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtoList;

        for (Posts boardEntity : boardEntities) {
            boardDtoList.add(new PostsMainResponseDto(boardEntity));
        }

        return boardDtoList;
    }

}