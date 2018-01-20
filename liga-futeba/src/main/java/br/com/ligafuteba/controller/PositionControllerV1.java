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

import br.com.ligafuteba.models.Position;
import br.com.ligafuteba.service.PositionService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/position")
public class PositionControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(PositionControllerV1.class);

    @Autowired
    private PositionService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Position position) {
        try {
            service.savePosition(position);
        } catch (Exception e) {
            return "Erro ao cadastrar Position: " + e.toString();
        }
        return "Atleta cadastrado com sucesso! (id = " + position.getId() + " nome = " + position.getNome() + ")";
    }

    @GetMapping("/listar")
    public @ResponseBody Iterable<Position> listar() {
        return service.findAllPositions();
    }

    @PutMapping("/atualizar/{id}")
    public Position atualizarPosition(@PathVariable("id") final Integer id, @RequestBody final Position position) {

        logger.info("Atualizando Position de id {}", id);
        Position positionAtual = service.findById(id);

        if (positionAtual == null) {
            logger.error("Position com id {} nao encontrada.", id);
            return positionAtual;
        }
        return service.updatePosition(position);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPosition(@PathVariable("id") final Integer id) {

        logger.info("Deletando Position de id {}", id);
        Position positionAtual = service.findById(id);

        if (positionAtual == null) {
            logger.error("Position com id {} nao encontrada.", id);
        }
        service.deletePositionById(id);
    }

    @DeleteMapping("/deletar")
    public void deletarTodasPosition() {

        logger.info("Deletando todas posicoes");
        service.deleteAllPositions();
    }
}
