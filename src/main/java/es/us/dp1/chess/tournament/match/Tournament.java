package es.us.dp1.chess.tournament.match;


import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import es.us.dp1.chess.tournament.model.NamedEntity;
import jakarta.persistence.OneToMany;

@Entity
@Getter
@Setter
public class Tournament extends NamedEntity {

    @Size(min = 3, max = 50)
    @NotBlank
    @Column(name = "location")
    String location;

    @NotNull
    @Column(name = "start_date")
    LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date")
    LocalDateTime endDate;

    @Min(2)
    @NotNull
    @Column(name = "max_participants")
    Integer maxParticipants;

    @OneToMany(mappedBy = "tournament")
    @NotNull
    List<ChessMatch> matches;
}
