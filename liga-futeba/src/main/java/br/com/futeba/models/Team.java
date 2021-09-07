package br.com.futeba.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "team")
public class Team implements Serializable {

    private static final long serialVersionUID = 3457244849292203050L;

    @Id
    @Column(name = "team_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Boolean away;
    @NotNull
    private String responsibleName;
    @NotNull
    private String phoneContact1;
    private String phoneContact2;
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    @ManyToOne(optional = false)
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    private Place place;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "team_player", joinColumns = {
            @JoinColumn(name = "team_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "player_id")
    })
    private List<Player> players;
    @OneToMany(mappedBy = "awayTeam")
    private List<Game> game;
}
