package com.keyin.tournaments;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRestRepository extends CrudRepository<Tournament, Long> {
    List<Tournament> findByStartDate(LocalDate startDate);
    List<Tournament> findByLocation(String location);
    List<Tournament> findByParticipatingMembers_Id(Long memberId);
}
