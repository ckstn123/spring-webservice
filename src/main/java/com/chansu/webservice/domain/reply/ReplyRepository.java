package com.chansu.webservice.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("SELECT r " +
            "FROM Reply r " +
            "WHERE r.postNo = ?1")
    List<Reply> findByPostNo(Long postNo);

    @Modifying
    @Query("UPDATE Reply r " +
            "SET r.replyContent = ?1 ," +
            "r.replyWriter = ?2 " +
            "WHERE r.replyNo = ?3")
    void modify(String replyContent, String replyWriter, Long replyNo);
}
