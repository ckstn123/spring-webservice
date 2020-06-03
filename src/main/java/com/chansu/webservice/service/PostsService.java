package com.chansu.webservice.service;

import com.chansu.webservice.domain.posts.Posts;
import com.chansu.webservice.dto.posts.PostInfoResponseDto;
import com.chansu.webservice.dto.posts.PostsModifyRequestDto;
import com.chansu.webservice.dto.posts.PostsSaveRequestDto;
import com.chansu.webservice.domain.posts.PostsRepository;
import com.chansu.webservice.dto.posts.PostsMainResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수

    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> getPostslist(Integer pageNum) {
        Page<Posts> page = postsRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<Posts> boardEntities = page.getContent();
        List<PostsMainResponseDto> boardDtoList = new ArrayList<>();

        for (Posts boardEntity : boardEntities) {
            boardDtoList.add(new PostsMainResponseDto(boardEntity));
        }

        return boardDtoList;
    }

    @Transactional
    public Long getPostsCount() {
        return postsRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        ArrayList<Integer> temp = new ArrayList<>();

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getPostsCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int pageNum = curPageNum, idx = 0; pageNum <= blockLastPageNum; pageNum++, idx++) {
            temp.add(pageNum);
            System.out.println(pageNum);
        }
        Integer[] pageList = new Integer[temp.size()];
        for(int idx = 0; idx < pageList.length; idx++){
            pageList[idx] = temp.get(idx);
        }
        return pageList;
    }

//    @Transactional(readOnly = true)
//    public List<PostsMainResponseDto> findAllDesc() {
//        //repository 결과로 넘어온 posts를 map을 통해 PostsMainResponseDto로 변환 후 List로 반환
//        return postsRepository.findAllDesc()
//                .map(PostsMainResponseDto::new)
//                .collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public PostInfoResponseDto findPosts(Long id){
        return postsRepository.findById(id).map(PostInfoResponseDto::new).get();
    }

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
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