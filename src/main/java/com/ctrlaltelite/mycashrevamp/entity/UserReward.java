package com.ctrlaltelite.mycashrevamp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_rewards")
public class UserReward {
    @Id
    private int reward_id;
    private int user_id;
    private int reward_points;
    private String reward_level;

}
