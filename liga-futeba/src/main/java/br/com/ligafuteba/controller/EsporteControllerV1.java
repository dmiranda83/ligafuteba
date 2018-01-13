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

import br.com.ligafuteba.models.Esporte;
import br.com.ligafuteba.service.EsporteService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/esporte")
public class EsporteControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(EsporteControllerV1.class);

    @Autowired
    private EsporteService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Esporte esporte) {

        try {

            service.salvarEsporte(esporte);

        } catch (Exception e) {
            return "Erro ao cadastrar esporte: " + e.toString();
        }
        return "Esporte cadastrado com sucesso! (id = " + esporte.getId() + ")";
    }

    @GetMapping("/listar")
    public @ResponseBody Iterable<Esporte> listar() {
        logger.info("Listando todos esportes");
        return service.localizarTodosEsportes();
    }

    @PutMapping("/atualizar/{id}")
    public Esporte atualizarEsporte(@PathVariable("id") final Integer id, @RequestBody final Esporte esporte) {

        logger.info("Atualizando Esporte de id {}", id);
        Esporte esporteAtual = service.localizarPorId(id);

        if (esporteAtual == null) {
            logger.error("Esporte com id {} nao encontrado.", id);
            return esporteAtual;
        }

        esporteAtual.setNome(esporte.getNome());

        return service.atualizarEsporte(esporteAtual);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPosicao(@PathVariable("id") final Integer id) {

        logger.info("Deletando Esporte de id {}", id);
        Esporte esporteAtual = service.localizarPorId(id);

        if (esporteAtual == null) {
            logger.error("Esporte com id {} nao encontrada.", id);
        }
        service.deletarPosicaoPorId(id);
    }

    @DeleteMapping("/deletar")
    public void deletarTodasPosicao() {

        logger.info("Deletando todos esportes");
        service.deletarTodasPosicoes();
    }
}
