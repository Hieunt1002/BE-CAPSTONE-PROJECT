package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capstoneproject.educonnect.Entity.Staff;

@Repository
public interface AddStaffRepository extends JpaRepository<Staff,Integer>{

}
