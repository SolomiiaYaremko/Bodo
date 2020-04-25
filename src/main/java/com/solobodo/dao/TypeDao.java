package com.solobodo.dao;

import com.solobodo.entity.EntertainmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeDao extends JpaRepository<EntertainmentType, Long> {
}
