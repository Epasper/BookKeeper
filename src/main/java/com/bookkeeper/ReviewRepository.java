package com.bookkeeper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Review SET book_id = :book_id WHERE review_id = :review_id")
    void updateBookId(@Param("review_id") int review_id, @Param("book_id") int book_id);
}
