package br.com.ligafuteba.controller;

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

import br.com.ligafuteba.dtos.EstatisticaAtletaDTO;
import br.com.ligafuteba.dtos.EstatisticasDTO;
import br.com.ligafuteba.models.Equipe;
import br.com.ligafuteba.service.EquipeService;

@RestController
@CrossOrigin (origins = "*") 
@RequestMapping("/api/v1/equipe")
public class EquipeControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(EquipeControllerV1.class);

    @Autowired
    private EquipeService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Equipe equipe) {

        try {

            service.salvarEquipe(equipe);

        } catch (Exception e) {
            return "Erro ao cadastrar equipe: " + e.toString();
        }
        return "Equipe cadastrado com sucesso! (id = " + equipe.getId() + " nome = " + equipe.getNome() + ")";
    }

    @GetMapping("/listar")
    public @ResponseBody Iterable<Equipe> listar() {
        return service.localizarTodasEquipes();
    }
    
    @GetMapping("/estatisticas/{year}")
    public @ResponseBody Iterable<EstatisticasDTO> getEstatisticas(@PathVariable("year") final Integer year) {
    	logger.info("Loading team stats");
    	return service.getEstatisticas(year);
    }

    @PutMapping("/atualizar/{id}")
    public Equipe atualizarEquipe(@PathVariable("id") final Integer id, @RequestBody final Equipe equipe) {

        logger.info("Atualizando Equipe de id {}", id);
        Equipe equipeAtual = service.localizarPorId(id);

        if (equipeAtual == null) {
            logger.error("Equipe com id {} nao encontrada.", id);
            return equipeAtual;
        }

        equipeAtual.setNome(equipe.getNome());
        equipeAtual.setVisitante(equipe.getVisitante());
        equipeAtual.setNome_responsavel(equipe.getNome_responsavel());
        equipeAtual.setTelefone_contato1(equipe.getTelefone_contato1());
        equipeAtual.setTelefone_contato2(equipe.getTelefone_contato2());
        equipeAtual.setEsporte(equipe.getEsporte());
        equipeAtual.setEstabelecimento(equipe.getEstabelecimento());
        equipeAtual.setAtletas(equipe.getAtletas());

        return service.atualizarEquipe(equipeAtual);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPosicao(@PathVariable("id") final Integer id) {

        logger.info("Deletando Atleta de id {}", id);
        Equipe equipeAtual = service.localizarPorId(id);

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
