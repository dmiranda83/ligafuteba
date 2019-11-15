package br.com.gamedate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gamedate.models.Game;
import br.com.gamedate.repositorys.GameRepository;
import br.com.gamedate.service.GameService;

@Service("JogoService")
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    @Override
    public Game salvarJogo(final Game jogo) {
        return repository.save(jogo);
    }

    @Override
    public List<Game> localizarTodosJogos(Integer year) {
        return repository.getJogos(year);
    }

    @Override
    public Game localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Game atualizarJogo(final Game jogo) {
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
