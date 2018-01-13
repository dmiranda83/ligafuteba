package br.com.ligafuteba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ligafuteba.models.Posicao;
import br.com.ligafuteba.repositorys.PosicaoRepository;
import br.com.ligafuteba.service.PosicaoService;

@Service("PosicaoService")
public class PosicaoServiceImpl implements PosicaoService {

    @Autowired
    private PosicaoRepository repository;

    @Override
    public Posicao salvarPosicao(final Posicao posicao) {
        return repository.save(posicao);
    }

    @Override
    public Iterable<Posicao> localizarTodasPosicao() {
        return repository.findAll();
    }

    @Override
    public Posicao atualizarPosicao(final Posicao posicao) {
        return salvarPosicao(posicao);
    }

    @Override
    public Posicao localizarPorId(final Integer id) {
        return repository.findOne(id);
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
