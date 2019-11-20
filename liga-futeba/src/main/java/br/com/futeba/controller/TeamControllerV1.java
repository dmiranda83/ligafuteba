package br.com.futeba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.futeba.dtos.StatisticsDTO;
import br.com.futeba.models.Team;
import br.com.futeba.service.TeamService;

@RestController
@CrossOrigin (origins = "*") 
@RequestMapping("/api/v1/equipe")
public class TeamControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TeamControllerV1.class);

    @Autowired
    private TeamService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Team equipe) {

        try {

            service.salvarEquipe(equipe);

        } catch (Exception e) {
            return "Erro ao cadastrar equipe: " + e.toString();
        }
        return "Equipe cadastrado com sucesso! (id = " + equipe.getId() + " nome = " + equipe.getNome() + ")";
    }

    @GetMapping("/listar")
    public @ResponseBody Iterable<Team> listar() {
        return service.localizarTodasEquipes();
    }
    
    @GetMapping("/estatisticas/{year}")
    public @ResponseBody Iterable<StatisticsDTO> getEstatisticas(@PathVariable("year") final Integer year) {
    	logger.info("Loading team stats");
    	return service.getEstatisticas(year);
    }

    @PutMapping("/atualizar/{id}")
    public Team atualizarEquipe(@PathVariable("id") final Integer id, @RequestBody final Team equipe) {

        logger.info("Atualizando Equipe de id {}", id);
        Team equipeAtual = service.localizarPorId(id);

        if (equipeAtual == null) {
            logger.error("Equipe com id {} nao encontrada.", id);
            return equipeAtual;
        }

        equipeAtual.setNome(equipe.getNome());
        equipeAtual.setVisitante(equipe.getVisitante());
        equipeAtual.setResponsibleName(equipe.getResponsibleName());
        equipeAtual.setPhoneContact1(equipe.getPhoneContact1());
        equipeAtual.setPhoneContact2(equipe.getPhoneContact2());
        equipeAtual.setEsporte(equipe.getEsporte());
        equipeAtual.setEstabelecimento(equipe.getEstabelecimento());
        equipeAtual.setAtletas(equipe.getAtletas());

        return service.atualizarEquipe(equipeAtual);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPosicao(@PathVariable("id") final Integer id) {

        logger.info("Deletando Atleta de id {}", id);
        Team equipeAtual = service.localizarPorId(id);

        if (equipeAtual == null) {
            logger.error("Atleta com id {} nao encontrada.", id);
        }
        service.deletarPosicaoPorId(id);
    }

    @DeleteMapping("/deletar")
    public void deletarTodasPosicao() {

        logger.info("Deletando todos atletas");
        service.deletarTodasPosicoes();
    }
}
