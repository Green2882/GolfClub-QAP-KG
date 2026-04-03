package com.keyin.members;

import com.keyin.tournaments.Tournament;
import com.keyin.tournaments.TournamentRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRestRepository memberRepository;

    @Autowired
    private TournamentRestRepository tournamentRepository;

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Iterable<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Iterable<Member> findMembersByName(String name) {
        return memberRepository.findByName(name);
    }

    public Iterable<Member> findMembersByMembershipType(String membershipType) {
        return memberRepository.findByMembershipType(membershipType);
    }

    public Iterable<Member> findMembersByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public Iterable<Member> findMembersByTournamentStartDate(LocalDate startDate) {
        List<Tournament> tournaments = tournamentRepository.findByStartDate(startDate);
        List<Member> members = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            members.addAll(tournament.getParticipatingMembers());
        }
        return members;
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    public Member updateMember(Long id, Member member) {
        Optional<Member> existingMember = memberRepository.findById(id);

        if (existingMember.isPresent()) {
            Member foundMember = existingMember.get();
            foundMember.setName(member.getName());
            foundMember.setAddress(member.getAddress());
            foundMember.setEmail(member.getEmail());
            foundMember.setMembershipType(member.getMembershipType());
            foundMember.setPhoneNumber(member.getPhoneNumber());
            foundMember.setStartDate(member.getStartDate());
            foundMember.setMembershipDuration(member.getMembershipDuration());

            return memberRepository.save(foundMember);
        }

        return null;
    }
}
