package com.hmsapp.repository;

import com.hmsapp.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<ImageData,Long> {
}
