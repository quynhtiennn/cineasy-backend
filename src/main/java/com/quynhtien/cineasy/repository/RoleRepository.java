package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
