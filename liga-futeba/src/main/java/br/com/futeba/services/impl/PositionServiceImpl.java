package br.com.futeba.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Position;
import br.com.futeba.repositories.PositionRepository;
import br.com.futeba.services.PositionService;

@Service("PositionService")
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionRepository repository;

	@Override
	public Position save(final Position posicao) {
		return repository.save(posicao);
	}

	@Override
	public Optional<Position> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Position> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Position> update(final Optional<Position> posicao) {
		return repository.saveAndFlush(posicao);
	}

	@Override
	public Optional<Position> findById(final Long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(final Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
}
