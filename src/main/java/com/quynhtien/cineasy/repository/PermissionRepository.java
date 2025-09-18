package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.Permission;
import com.quynhtien.cineasy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
