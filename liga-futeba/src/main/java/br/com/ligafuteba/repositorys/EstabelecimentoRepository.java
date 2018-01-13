package br.com.ligafuteba.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ligafuteba.models.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {

}
