package br.com.ligafuteba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ligafuteba.models.Estabelecimento;
import br.com.ligafuteba.repositorys.EstabelecimentoRepository;
import br.com.ligafuteba.service.EstabelecimentoService;

@Service("EstabelecimentoService")
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repository;

    @Override
    public Estabelecimento salvarEstabelecimento(final Estabelecimento estabelecimento) {
        return repository.save(estabelecimento);
    }

    @Override
    public Iterable<Estabelecimento> localizarTodosEstabelecimento() {
        return repository.findAll();
    }

    @Override
    public Estabelecimento atualizarEstabelecimento(final Estabelecimento estabelecimento) {
        return repository.save(estabelecimento);
    }

    @Override
    public Estabelecimento localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void deletarEstabelecimentoPorId(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deletarTodosEstabelecimentos() {
        repository.deleteAll();
    }
}
