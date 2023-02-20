package com.yg.cm.repository.member;

import com.yg.cm.entity.member.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
