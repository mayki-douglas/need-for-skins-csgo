package com.proj.nfscsgo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.proj.nfscsgo.model.Skins;
import com.proj.nfscsgo.repository.SkinsRepository;


@Service
@Transactional
@Controller
public class SkinsController {
	
	@Autowired
	private SkinsRepository rep;
	
	public List<Skins> listarTodos(){
		return this.rep.findAll();
	}
	
	public void salvar(Skins sks) {
		this.rep.save(sks);
	}
	
	public Skins getSkins(long id) {
		return this.rep.findById(id).get();
	}
	
	public void excluir(long id) {
		this.rep.deleteById(id);
	}
	
	@RequestMapping("/")
	public String chamarIndex() {
		return "index";
	}
	
	@RequestMapping("/lista")
	public String chamarLista(Model modelo) {
		List<Skins> listaSkins = this.listarTodos();
		modelo.addAttribute("listaSkins", listaSkins);
		return "lista";
	}
	
	@RequestMapping("/novo")
	public ModelAndView chamarNovo() {
		ModelAndView modelo = new ModelAndView ("novo");
		Skins objSkins = new Skins();
		modelo.addObject("skins", objSkins);
		return modelo;
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView chamarEditar(@PathVariable(name = "id") long id) {
		ModelAndView modelo = new ModelAndView("editar");
		Skins objSkins = this.getSkins(id);
		modelo.addObject("skins", objSkins);
		return modelo;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String chamarSalvar(@ModelAttribute("skins") Skins objSkins) {
		this.salvar(objSkins);
		return "redirect:/";
	}
	
	@RequestMapping("/excluir/{id}")
	public String chamarExcluir(@PathVariable(name = "id") long id) {
		this.excluir(id);
		return "redirect:/";
	}

}
