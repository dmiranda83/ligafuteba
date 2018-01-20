package br.com.ligafuteba.controller;

import java.text.ParseException;
import java.util.List;

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
import br.com.ligafuteba.models.Jogo;
import br.com.ligafuteba.service.JogoService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/jogo")
public class JogoControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(JogoControllerV1.class);

    @Autowired
    private JogoService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Jogo jogo) throws ParseException {

        try {
            service.salvarJogo(jogo);

        } catch (Exception e) {
            return "Erro ao cadastrar jogo: " + e.toString();
        }
        return "Jogo cadastrado com sucesso! (id = " + jogo.getId() + ")";
    }

    @GetMapping("/listar/{year}")
    public @ResponseBody List<Jogo> listar(@PathVariable("year") final Integer year) {
        return service.localizarTodosJogos(year);
    }

    @PutMapping("/atualizar/{id}")
    public Jogo atualizarJogo(@PathVariable("id") final Integer id, @RequestBody final Jogo jogo) {

        logger.info("Atualizando Jogo de id {}", id);
        Jogo jogoAtual = service.localizarPorId(id);

        if (jogoAtual == null) {
            logger.error("Jogo com id {} nao encontrada.", id);
            return jogoAtual;
        }
        
        logger.info("Gols Mandante" + jogo.getGamePlayerData());
        logger.info("Gols Visitante" + jogo.getGolsVisitante());
        logger.info("Ass Mandante" + jogo.getAssMandante());
        logger.info("Ass Visitante" + jogo.getAssVisitante());

        jogoAtual.setEquipeMandante(jogo.getEquipeMandante());
        jogoAtual.setEquipeVisitante(jogo.getEquipeVisitante());
        jogoAtual.setGamePlayerData(jogo.getGamePlayerData());
        jogoAtual.setGolsVisitante(jogo.getGolsVisitante());
        jogoAtual.setAssMandante(jogo.getAssMandante());
        jogoAtual.setAssVisitante(jogo.getAssVisitante());
        jogoAtual.setTotalGolsMandante(jogo.getTotalGolsMandante());
        jogoAtual.setTotalGolsVisitante(jogo.getTotalGolsVisitante());
        jogoAtual.setPontos(jogo.getPontos());

        return service.atualizarJogo(jogoAtual);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPosicao(@PathVariable("id") final Integer id) {

        logger.info("Deletando Jogo de id {}", id);
        Jogo jogoAtual = service.localizarPorId(id);

        if (jogoAtual == null) {
            logger.error("Jogo com id {} nao encontrada.", id);
        }
        service.deletarPosicaoPorId(id);
    }

    @DeleteMapping("/deletar")
    public void deletarTodasPosicao() {

        logger.info("Deletando todos os jogos");
        service.deletarTodasPosicoes();
    }
}
