package br.com.futeba.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.futeba.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    public Optional<Player> findById(@Param("idParam") Integer id);
    
    @Query(value = "SELECT nome, gols, assists , freq, media FROM atletas " +
				"INNER JOIN (SELECT atleta_id, SUM(qtd_gols) AS gols FROM gols WHERE gol_id IN (SELECT game_player_data_gol_id FROM jogos_game_player_data WHERE jogos_jogo_id IN (SELECT jogo_id FROM jogos WHERE YEAR(data) = :year)) GROUP BY atleta_id) a USING (atleta_id) " + 
				"INNER JOIN (SELECT atleta_id, SUM(qtd_assist) AS assists FROM assistencias WHERE assist_id IN (SELECT ass_mandante_assist_id FROM jogos_ass_mandante WHERE jogos_jogo_id IN (SELECT jogo_id FROM jogos WHERE YEAR(data) = :year)) GROUP BY atleta_id) b USING (atleta_id) " +
				"INNER JOIN (SELECT atleta_id, (SUM(presence) / (SELECT COUNT(*) FROM jogos WHERE YEAR(data) = :year)) AS freq FROM gols WHERE gol_id IN (SELECT game_player_data_gol_id FROM jogos_game_player_data WHERE jogos_jogo_id IN (SELECT jogo_id FROM jogos WHERE YEAR(data) = :year)) GROUP BY atleta_id) c USING (atleta_id) " +
				"INNER JOIN (SELECT atleta_id, (SUM(qtd_gols) / SUM(played_game))AS media FROM gols WHERE gol_id IN (SELECT game_player_data_gol_id FROM jogos_game_player_data WHERE jogos_jogo_id IN (SELECT jogo_id FROM jogos WHERE YEAR(data) = 2018)) GROUP BY atleta_id) d USING (atleta_id)" + 
				"ORDER BY gols DESC, assists DESC, freq DESC, nome ASC", nativeQuery = true)
    public List<?> getEstatisticasAtleta(@Param("year") Integer year);

	public Optional<Player> saveAndFlush(Optional<Player> atleta);
    
}
