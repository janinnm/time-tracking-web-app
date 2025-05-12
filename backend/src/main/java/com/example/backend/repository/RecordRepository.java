package com.example.backend.repository;

import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<UserRecord, Long> {

    List<UserRecord> findAllByUser(User user);
    UserRecord findUserRecordByRecordId(Long recordId);
}
