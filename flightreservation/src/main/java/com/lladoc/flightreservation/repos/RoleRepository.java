package com.lladoc.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lladoc.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
