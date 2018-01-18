package br.com.ligafuteba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ligafuteba.models.Jogo;
import br.com.ligafuteba.repositorys.JogoRepository;
import br.com.ligafuteba.service.JogoService;

@Service("JogoService")
public class JogoServiceImpl implements JogoService {

    @Autowired
    private JogoRepository repository;

    @Override
    public Jogo salvarJogo(final Jogo jogo) {
        return repository.save(jogo);
    }

    @Override
    public Iterable<Jogo> localizarTodosJogos() {
        return repository.getJogos();
    }

    @Override
    public Jogo localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Jogo atualizarJogo(final Jogo jogo) {
        return repository.saveAndFlush(jogo);
    }

    @Override
    public void deletarPosicaoPorId(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deletarTodasPosicoes() {
        repository.deleteAll();
    }

}
