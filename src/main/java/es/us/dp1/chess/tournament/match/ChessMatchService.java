package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.chess.tournament.exceptions.UnfeasibleMatchException;
import es.us.dp1.chess.tournament.user.User;

@Service
public class ChessMatchService {

    MatchRepository repo;    

    @Autowired
    public ChessMatchService(MatchRepository repo) {
        this.repo = repo;        
    }

    
    @Transactional(readOnly = true)
    public Optional<ChessMatch> getMatchById(Integer id) {
        return repo.findById(id);
    }

    @Transactional(rollbackFor = UnfeasibleMatchException.class)
    public ChessMatch save(ChessMatch m) throws UnfeasibleMatchException {
        User winner = m.winner;
        if(m.finish != null && m.start.isAfter(m.finish)){
            throw new UnfeasibleMatchException("The start date cannot be after the finish date");
        }

        String whitePlayer = m.whitePlayer.getUsername();
        String blackPlayer = m.blackPlayer.getUsername();
        String winnerPlayer = winner != null ? winner.getUsername() : null;

        if(winner!=null && !(winnerPlayer.equals(whitePlayer) || winnerPlayer.equals(blackPlayer))){
            throw new UnfeasibleMatchException("The winner has to be one of the players inside the match");
        }

        return repo.save(m);
    }
    
    

    @Transactional(readOnly = true)
    public List<ChessMatch> getMatches() {
        return repo.findAll();
    }    
    

    

}
