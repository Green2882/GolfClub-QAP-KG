package com.keyin.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Member>> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findMemberById(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Member>> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String membershipType,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tournamentStartDate) {
        
        if (name != null) {
            return ResponseEntity.ok(memberService.findMembersByName(name));
        } else if (membershipType != null) {
            return ResponseEntity.ok(memberService.findMembersByMembershipType(membershipType));
        } else if (phoneNumber != null) {
            return ResponseEntity.ok(memberService.findMembersByPhoneNumber(phoneNumber));
        } else if (tournamentStartDate != null) {
            return ResponseEntity.ok(memberService.findMembersByTournamentStartDate(tournamentStartDate));
        }
        
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @PostMapping()
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.createMember(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.ok("Member with id " + id + " deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody Member member) {
        memberService.updateMember(id, member);
        return ResponseEntity.ok("Member with id " + id + " updated successfully");
    }
}
