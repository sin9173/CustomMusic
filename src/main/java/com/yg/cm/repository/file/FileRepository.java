package com.yg.cm.repository.file;

import com.yg.cm.entity.file.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Long> {
}
