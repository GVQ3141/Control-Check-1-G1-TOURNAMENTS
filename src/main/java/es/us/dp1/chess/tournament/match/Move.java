package es.us.dp1.chess.tournament.match;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import es.us.dp1.chess.tournament.model.BaseEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
@Getter
@Setter
public class Move extends BaseEntity {

    @NotNull
    @Min(1)
    @Column(name = "turn")
    Integer turn;

    @NotNull
    @Min(1)
    @Max(8)
    @Column(name = "origin_x")
    Integer originX;

    @NotNull
    @Min(1)
    @Max(8)
    @Column(name = "origin_y")
    Integer originY;

    @NotNull
    @Min(1)
    @Max(8)
    @Column(name = "destination_x")
    Integer destinationX;

    @NotNull
    @Min(1)
    @Max(8)
    @Column(name = "destination_y")
    Integer destinationY;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "match_id")
    ChessMatch match;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "piece_id")
    Piece piece;

    @OneToOne
    @JoinColumn(name = "capture_id")
    Piece capture;
}
