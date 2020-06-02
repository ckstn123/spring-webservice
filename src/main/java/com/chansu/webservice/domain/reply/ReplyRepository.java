package com.chansu.webservice.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByPost_no(Long post_no);

    @Modifying
    @Query("UPDATE Reply p " +
            "SET p.reply_content = ?1 " +
            "WHERE p.reply_no = ?2")
    void modify(String reply_content, Long reply_no);
}
