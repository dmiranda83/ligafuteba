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

import br.com.ligafuteba.models.Estabelecimento;
import br.com.ligafuteba.service.EstabelecimentoService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/estabelecimento")
public class EstabelecimentoControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(EstabelecimentoControllerV1.class);

    @Autowired
    private EstabelecimentoService service;

    @PostMapping("/salvar")
    public String cadastrar(@RequestBody final Estabelecimento estabelecimento) {

        try {

            service.salvarEstabelecimento(estabelecimento);

        } catch (Exception e) {
            return "Erro ao cadastrar estabelecimento: " + e.toString();
        }
        return "Estabelecimento cadastrado com sucesso! (id = " + estabelecimento.getId() + " nome = " + estabelecimento.getNome() + ")";
    }

    @GetMapping("/listar")
    public @ResponseBody Iterable<Estabelecimento> listar() {
        logger.info("Listando todos estabelecimentos");
        return service.localizarTodosEstabelecimento();
    }

    @PutMapping("/atualizar/{id}")
    public Estabelecimento atualizarEstabelecimento(@PathVariable("id") final Integer id, @RequestBody final Estabelecimento estabelecimento) {

        logger.info("Atualizando Estabelecimento de id {}", id);
        Estabelecimento estabelecimentoAtual = service.localizarPorId(id);

        if (estabelecimentoAtual == null) {
            logger.error("Estabelecimento com id {} nao encontrada.", id);
            return estabelecimentoAtual;
        }

        estabelecimentoAtual.setNome(estabelecimento.getNome());
        estabelecimentoAtual.setTipo(estabelecimento.getTipo());
        estabelecimentoAtual.setEndereco(estabelecimento.getEndereco());
        estabelecimentoAtual.setCidade(estabelecimento.getCidade());
        estabelecimentoAtual.setBairro(estabelecimento.getBairro());
        estabelecimentoAtual.setCep(estabelecimento.getCep());

        return service.atualizarEstabelecimento(estabelecimentoAtual);
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarEstabelecimento(@PathVariable("id") final Integer id) {

        logger.info("Deletando Estabelecimento de id {}", id);
        Estabelecimento estabelecimentoAtual = service.localizarPorId(id);

        if (estabelecimentoAtual == null) {
            logger.error("Estabelecimento com id {} nao encontrada.", id);
        }
        service.deletarEstabelecimentoPorId(id);
        return "Estabelecimento deletado com sucesso! (id = " + estabelecimentoAtual.getId() + " nome = " + estabelecimentoAtual.getNome() + ")";
    }

    @DeleteMapping("/deletar")
    public void deletarTodasEstabelecimento() {

        logger.info("Deletando todos estabelecimentos");
        service.deletarTodosEstabelecimentos();
    }
}
