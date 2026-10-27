package es.us.dp1.chess.tournament.match;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
public class PieceService {

    PieceRepository repository;

    public PieceService(Optional<PieceRepository> repository) {
        this.repository = repository.orElse(null);
    }

    @Transactional
    public List<Piece> getPiecesByColorAndType(PieceColor color, String typeName) {
        return repository.findByColorAndTypeName(color, typeName);
    }

    @Transactional
    public List<Piece> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Piece getById(Integer i) {
        return repository.findById(i).orElse(null);
    }

    @Transactional
    public Piece save(Piece piece) {
        return repository.save(piece);
    }
    
}
