package com.ctrlaltelite.mycashrevamp.repository;

import com.ctrlaltelite.mycashrevamp.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
    Block findFirstByOrderByIdDesc();

}
