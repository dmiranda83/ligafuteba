package br.com.ligafuteba.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ligafuteba.models.Esporte;

public interface EsporteRepository extends JpaRepository<Esporte, Integer> {

}
