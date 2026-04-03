package com.keyin.members;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRestRepository extends CrudRepository<Member, Long> {
    List<Member> findByName(String name);
    List<Member> findByMembershipType(String membershipType);
    List<Member> findByPhoneNumber(String phoneNumber);
}
