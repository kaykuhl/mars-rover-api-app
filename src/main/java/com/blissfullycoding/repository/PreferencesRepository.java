package com.blissfullycoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blissfullycoding.dto.HomeDto;

public interface PreferencesRepository extends JpaRepository<HomeDto, Long> {

	  HomeDto findByUserId(Long userId);

}
