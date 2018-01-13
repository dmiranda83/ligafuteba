package br.com.ligafuteba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ligafuteba.models.Esporte;
import br.com.ligafuteba.repositorys.EsporteRepository;
import br.com.ligafuteba.service.EsporteService;

@Service("EsporteService")
public class EsporteServiceImpl implements EsporteService {

    @Autowired
    private EsporteRepository repository;

    @Override
    public Esporte salvarEsporte(final Esporte esporte) {
        return repository.save(esporte);
    }

    @Override
    public Iterable<Esporte> localizarTodosEsportes() {
        return repository.findAll();
    }

    @Override
    public Esporte localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Esporte atualizarEsporte(final Esporte esporte) {
        return repository.saveAndFlush(esporte);
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
