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
import br.com.ligafuteba.models.Atleta;
import br.com.ligafuteba.service.AtletaService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/atleta")
public class AtletaControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(AtletaControllerV1.class);

    @Autowired
    private AtletaService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Atleta atleta) {

        try {
            service.salvarAtleta(atleta);

        } catch (Exception e) {
            return "Erro ao cadastrar atleta: " + e.toString();
        }
        return "Atleta cadastrado com sucesso! (id = " + atleta.getId() + " nome = " + atleta.getNome() + ")";
    }

    @GetMapping("/listar")
    public @ResponseBody Iterable<Atleta> listar() {
        logger.info("Listando todos atletas");
        return service.localizarTodosAtletas();
    }
    @GetMapping("/estatisticas")
    public @ResponseBody Iterable<EstatisticaAtletaDTO> getArtilharia() {
    	logger.info("Listando Artilheiros");
    	return service.getEstatisticasAtletas();
    }

    @PutMapping("/atualizar/{id}")
    public Atleta atualizarAtleta(@PathVariable("id") final Integer id, @RequestBody final Atleta atleta) {

        logger.info("Atualizando Atleta de id {}", id);
        Atleta atletaAtual = service.localizarPorId(id);

        if (atletaAtual == null) {
            logger.error("Atleta com id {} nao encontrada.", id);
            return atletaAtual;
        }

        atletaAtual.setNome(atleta.getNome());
        atletaAtual.setPosicoes(atleta.getPosicoes());
        atletaAtual.setEquipes(atleta.getEquipes());

        return service.atualizarAtleta(atletaAtual);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPosicao(@PathVariable("id") final Integer id) {

        logger.info("Deletando Atleta de id {}", id);
        Atleta atletaAtual = service.localizarPorId(id);

        if (atletaAtual == null) {
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
