package com.saraconference.backend.repository;


import com.saraconference.backend.entity.ParticipantProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantProfileRepository extends JpaRepository<ParticipantProfile, Long> {

}
