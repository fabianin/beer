package br.ufes.dcel.beer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.dcel.beer.model.Cerveja;
import br.ufes.dcel.beer.model.TipoCerveja;
import br.ufes.dcel.beer.repository.Cervejas;

@Controller
public class BeerController {
	
	@Autowired
	private Cervejas cervejas;
	
	@RequestMapping("/beer")
	public ModelAndView lista(Cerveja cerveja){
		ModelAndView mv = new ModelAndView("listagem");
		/*List<Cerveja> cervejas = new ArrayList<Cerveja>();
		Cerveja c = new Cerveja();
		c.setId(1L);
		c.setNome("Lager");
		c.setPaisOrigem("Bangladesh");
		c.setValor(new BigDecimal(100));
		c.setVolume(10);
		c.setTipo(TipoCerveja.LAGER);
		
		Cerveja c2 = new Cerveja();
		c2.setId(2L);
		c2.setNome("Ale");
		c2.setPaisOrigem("Bangladesh");
		c2.setValor(new BigDecimal(200));
		c2.setVolume(5);
		c2.setTipo(TipoCerveja.ALE);
		
		cervejas.add(c);
		cervejas.add(c2);*/
		
		mv.addObject("cervejas",cervejas.findAll());
		mv.addObject("tipos",TipoCerveja.values());
		
		return mv;
	}
	

	@RequestMapping("/beer/angular")
	public ModelAndView listar(Cerveja cerveja){
		ModelAndView mv = new ModelAndView("listagem2");
		/*List<Cerveja> cervejas = new ArrayList<Cerveja>();
		Cerveja c = new Cerveja();
		c.setId(1L);
		c.setNome("Lager");
		c.setPaisOrigem("Bangladesh");
		c.setValor(new BigDecimal(100));
		c.setVolume(10);
		c.setTipo(TipoCerveja.LAGER);
		
		Cerveja c2 = new Cerveja();
		c2.setId(2L);
		c2.setNome("Ale");
		c2.setPaisOrigem("Bangladesh");
		c2.setValor(new BigDecimal(200));
		c2.setVolume(5);
		c2.setTipo(TipoCerveja.ALE);
		
		cervejas.add(c);
		cervejas.add(c2);*/
		
		mv.addObject("cervejas",cervejas.findAll());
		mv.addObject("tipos",TipoCerveja.values());
		
		return mv;
	}
	
	
	@RequestMapping("/beer/novo")
	public ModelAndView novo(Cerveja cerveja){
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("tipos",TipoCerveja.values());
		return mv;
	}
	
	@RequestMapping(value="/beer/novo",method=RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes attribute){
		
		if(result.hasErrors()){
			//System.out.println("****"+cerveja.getNome()+"****");
			 return novo(cerveja);
		}
		cervejas.save(cerveja);
		attribute.addFlashAttribute("mensagem","cerveja salva com sucesso");
		return new ModelAndView("redirect:/beer/novo");
		
	}
	
	@RequestMapping("/beer/{id}")
	public ModelAndView editar(@PathVariable("id") Cerveja cerveja, RedirectAttributes attribute){
		//Cerveja cerveja = cervejas.findOne(id);
		if(cerveja==null){
			attribute.addFlashAttribute("mensagem","cerveja não encontrada");
			return new ModelAndView("redirect:/beer/");
		}else{
			ModelAndView mv = new ModelAndView("edicao");
			mv.addObject("cerveja",cerveja);
			mv.addObject("tipos",TipoCerveja.values());
			return mv;
		}
	}
	
	@RequestMapping(value="/beer/editar",method=RequestMethod.POST)
	public ModelAndView salvarEdicao(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes attribute){
		
		if(result.hasErrors()){
			//System.out.println("****"+cerveja.getNome()+"****");
			 return novo(cerveja);
		}
		cervejas.save(cerveja);
		attribute.addFlashAttribute("mensagemS","cerveja salva com sucesso");
		return new ModelAndView("redirect:/beer/");
		
	}
	@RequestMapping("/beer/deletar/{id}")
	public ModelAndView deletar(@PathVariable("id") Cerveja cerveja, RedirectAttributes attribute){
		if(cerveja==null){
			attribute.addFlashAttribute("mensagem","cerveja não encontrada");
			return new ModelAndView("redirect:/beer/");
		}else{
			ModelAndView mv = new ModelAndView("delete");
			mv.addObject("cerveja",cerveja);
			mv.addObject("tipos",TipoCerveja.values());
			return mv;
		}
	}
	@RequestMapping("/beer/deletar/remove/{id}")
	public ModelAndView deletarDeVez(@PathVariable("id") Cerveja cerveja, RedirectAttributes attribute){
		cervejas.delete(cerveja);
		return new ModelAndView("redirect:/beer");
	}
	
	@RequestMapping("/beer/pesquisa")
	public ModelAndView pesquisa(Cerveja cerveja){
		ModelAndView mv = new ModelAndView("listagem");
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.STARTING);
		Example<Cerveja> example = Example.of(cerveja,matcher);
		List<Cerveja> todasCervejas = cervejas.findAll(example);
		mv.addObject("tipos",TipoCerveja.values());
		mv.addObject("cervejas",todasCervejas);
		return mv;
	}
	
	
}
