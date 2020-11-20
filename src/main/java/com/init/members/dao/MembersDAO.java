package com.init.members.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.members.entitys.Members;

public interface MembersDAO extends JpaRepository<Members, Long> {

}

