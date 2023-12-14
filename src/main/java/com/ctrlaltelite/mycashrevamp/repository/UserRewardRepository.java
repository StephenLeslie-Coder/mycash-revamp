package com.ctrlaltelite.mycashrevamp.repository;

import com.ctrlaltelite.mycashrevamp.entity.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRewardRepository extends JpaRepository<UserReward, Integer> {
}