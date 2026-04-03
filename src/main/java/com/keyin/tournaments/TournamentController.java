package com.keyin.tournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/tournaments")
@CrossOrigin
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tournament>> getTournamentById(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.findTournamentById(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.findAllTournaments());
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Tournament>> searchTournaments(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Long memberId) {
        
        if (startDate != null) {
            return ResponseEntity.ok(tournamentService.findTournamentsByStartDate(startDate));
        } else if (location != null) {
            return ResponseEntity.ok(tournamentService.findTournamentsByLocation(location));
        } else if (memberId != null) {
            return ResponseEntity.ok(tournamentService.findTournamentsByMemberId(memberId));
        }
        
        return ResponseEntity.ok(tournamentService.findAllTournaments());
    }

    @PostMapping()
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        return ResponseEntity.ok(tournamentService.createTournament(tournament));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTournamentById(@PathVariable Long id) {
        tournamentService.deleteTournamentById(id);
        return ResponseEntity.ok("Tournament with id " + id + " deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        tournamentService.updateTournament(id, tournament);
        return ResponseEntity.ok("Tournament with id " + id + " updated successfully");
    }

    @PostMapping("/{tournamentId}/add-member/{memberId}")
    public ResponseEntity<Tournament> addMemberToTournament(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        Tournament updatedTournament = tournamentService.addMemberToTournament(tournamentId, memberId);
        if (updatedTournament != null) {
            return ResponseEntity.ok(updatedTournament);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
