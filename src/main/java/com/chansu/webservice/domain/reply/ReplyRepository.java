package com.chansu.webservice.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByPostNo(Long postNo);

    @Modifying
    @Query("UPDATE Reply p " +
            "SET p.replyContent = ?1 " +
            "WHERE p.replyNo = ?2")
    void modify(String replyContent, String replyWriter, Long replyNo);
}
