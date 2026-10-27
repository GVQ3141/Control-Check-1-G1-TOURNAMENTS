package es.us.dp1.chess.tournament.match;


import es.us.dp1.chess.tournament.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import es.us.dp1.chess.tournament.exceptions.ResourceNotFoundException;



@RestController
@RequestMapping("/api/v1/matches")
@PreAuthorize("hasRole('PLAYER')")
public class ChessMatchController {

    UserService userService;
    ChessMatchService matchService;

    @Autowired
    public ChessMatchController(ChessMatchService service,UserService userService){
        this.matchService=service;
        this.userService=userService;    
    }

    @GetMapping
    public List<ChessMatch> getMatches() {
        List<ChessMatch> matches = matchService.getMatches();
        return new ResponseEntity<>(matches, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public ChessMatch getMatchById(@PathVariable Integer id) {
        ChessMatch match = matchService.getMatchById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " + id));
        return new ResponseEntity<>(match, HttpStatus.OK).getBody();
    }

}