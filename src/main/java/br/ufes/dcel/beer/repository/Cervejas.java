package br.ufes.dcel.beer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufes.dcel.beer.model.Cerveja;

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long>{

}
