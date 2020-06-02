package com.chansu.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

//DAO 역할 DB Layer 접근자
public interface PostsRepository extends JpaRepository<Posts, Long> {
    //게시글 목록 조회
    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.id DESC")
    Stream<Posts> findAllDesc();

    @Modifying
    @Query("UPDATE Posts p " +
            "SET p.title = ?1, " +
            "p.author = ?2, " +
            "p.content = ?3 " +
            "WHERE p.id = ?4")
    void modify(String title, String author, String content, Long id);
    
    List<Posts> findByTitleContaining(String keyword);
}