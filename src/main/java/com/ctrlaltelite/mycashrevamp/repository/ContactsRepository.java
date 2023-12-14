package com.ctrlaltelite.mycashrevamp.repository;

import com.ctrlaltelite.mycashrevamp.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
}