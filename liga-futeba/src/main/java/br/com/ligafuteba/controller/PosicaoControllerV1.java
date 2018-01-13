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

import br.com.ligafuteba.models.Posicao;
import br.com.ligafuteba.service.PosicaoService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/posicao")
public class PosicaoControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(PosicaoControllerV1.class);

    @Autowired
    private PosicaoService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Posicao posicao) {

        try {

            service.salvarPosicao(posicao);

        } catch (Exception e) {
            return "Erro ao cadastrar posicao: " + e.toString();
        }
        return "Atleta cadastrado com sucesso! (id = " + posicao.getId() + " nome = " + posicao.getNome() + ")";
    }

    @GetMapping("/listar")
    public @ResponseBody Iterable<Posicao> listar() {
        return service.localizarTodasPosicao();
    }

    @PutMapping("/atualizar/{id}")
    public Posicao atualizarPosicao(@PathVariable("id") final Integer id, @RequestBody final Posicao posicao) {

        logger.info("Atualizando Posicao de id {}", id);
        Posicao posicaoAtual = service.localizarPorId(id);

        if (posicaoAtual == null) {
            logger.error("Posicao com id {} nao encontrada.", id);
            return posicaoAtual;
        }
        return service.atualizarPosicao(posicao);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPosicao(@PathVariable("id") final Integer id) {

        logger.info("Deletando Posicao de id {}", id);
        Posicao posicaoAtual = service.localizarPorId(id);

        if (posicaoAtual == null) {
            logger.error("Posicao com id {} nao encontrada.", id);
        }
        service.deletarPosicaoPorId(id);
    }

    @DeleteMapping("/deletar")
    public void deletarTodasPosicao() {

        logger.info("Deletando todas posicoes");
        service.deletarTodasPosicoes();
    }
}
