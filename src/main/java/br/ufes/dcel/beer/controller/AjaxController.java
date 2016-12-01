package br.ufes.dcel.beer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.dcel.beer.model.Cerveja;
import br.ufes.dcel.beer.repository.Cervejas;

@RestController
public class AjaxController {
	
	@Autowired
	private Cervejas cervejas;
	
	@GetMapping("/beer/pesquisa")
	public List<Cerveja> pesquisa(Cerveja cerveja){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.STARTING);
		Example<Cerveja> example = Example.of(cerveja,matcher);
		List<Cerveja> todasCervejas = cervejas.findAll(example);
		return todasCervejas;
	}
	
	@DeleteMapping("/beer/deletar")
	public void remover(Cerveja cerveja){
		cervejas.delete(cerveja);
		
	}

}
