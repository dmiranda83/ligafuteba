package br.com.gamedate.controller;

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

import br.com.gamedate.models.Category;
import br.com.gamedate.service.CategoryService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/esporte")
public class TeamCategoryControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TeamCategoryControllerV1.class);

    @Autowired
    private CategoryService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Category esporte) {

        try {

            service.salvarEsporte(esporte);

        } catch (Exception e) {
            return "Erro ao cadastrar esporte: " + e.toString();
        }
        return "Esporte cadastrado com sucesso! (id = " + esporte.getId() + ")";
    }

    @GetMapping("/listar")
    public @ResponseBody Iterable<Category> listar() {
        logger.info("Listando todos esportes");
        return service.localizarTodosEsportes();
    }

    @PutMapping("/atualizar/{id}")
    public Category atualizarEsporte(@PathVariable("id") final Integer id, @RequestBody final Category esporte) {

        logger.info("Atualizando Esporte de id {}", id);
        Category esporteAtual = service.localizarPorId(id);

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
        Category esporteAtual = service.localizarPorId(id);

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
