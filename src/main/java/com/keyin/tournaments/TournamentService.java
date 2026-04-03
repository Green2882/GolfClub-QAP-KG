package com.keyin.tournaments;

import com.keyin.members.Member;
import com.keyin.members.MemberRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRestRepository tournamentRepository;

    @Autowired
    private MemberRestRepository memberRepository;

    public Optional<Tournament> findTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public Iterable<Tournament> findAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Iterable<Tournament> findTournamentsByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public Iterable<Tournament> findTournamentsByLocation(String location) {
        return tournamentRepository.findByLocation(location);
    }

    public Iterable<Tournament> findTournamentsByMemberId(Long memberId) {
        return tournamentRepository.findByParticipatingMembers_Id(memberId);
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public void deleteTournamentById(Long id) {
        tournamentRepository.deleteById(id);
    }

    public Tournament updateTournament(Long id, Tournament tournament) {
        Optional<Tournament> existingTournament = tournamentRepository.findById(id);

        if (existingTournament.isPresent()) {
            Tournament foundTournament = existingTournament.get();
            foundTournament.setStartDate(tournament.getStartDate());
            foundTournament.setEndDate(tournament.getEndDate());
            foundTournament.setLocation(tournament.getLocation());
            foundTournament.setEntryFee(tournament.getEntryFee());
            foundTournament.setCashPrizeAmount(tournament.getCashPrizeAmount());
            foundTournament.setParticipatingMembers(tournament.getParticipatingMembers());

            return tournamentRepository.save(foundTournament);
        }

        return null;
    }

    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (tournamentOptional.isPresent() && memberOptional.isPresent()) {
            Tournament tournament = tournamentOptional.get();
            Member member = memberOptional.get();
            tournament.addParticipatingMember(member);
            return tournamentRepository.save(tournament);
        }
        return null;
    }
}
