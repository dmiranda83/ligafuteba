package br.com.gamedate.service;

import br.com.gamedate.models.Place;

public interface PlaceService {

    Place salvarEstabelecimento(Place estabelecimento);

    Iterable<Place> localizarTodosEstabelecimento();

    Place localizarPorId(Integer id);

    void deletarEstabelecimentoPorId(Integer id);

    void deletarTodosEstabelecimentos();

    Place atualizarEstabelecimento(Place estabelecimento);
}
