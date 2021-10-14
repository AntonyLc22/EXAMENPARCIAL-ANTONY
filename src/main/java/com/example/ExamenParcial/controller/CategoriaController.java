/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ExamenParcial.controller;

import com.example.ExamenParcial.entity.Categoria;
import com.example.ExamenParcial.repository.CategRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Angel
 */

    @Controller
public class CategoriaController {
    @Autowired
    private CategRepository CategRepository;
    @RequestMapping("/")
    public String mensaje(Model model){
        model.addAttribute("mensaje", "Bienvenidos");
        return "index";
    }
    @RequestMapping("/categorias")
    public String cate(Model model){
        model.addAttribute("categorias", CategRepository.findAll());
        return "categoria";
    }
    @RequestMapping("/form")
    public String create(Model model) {
        return "addCate";
    }
    @RequestMapping("/addCate")
    public String guardar(@RequestParam String titulo, @RequestParam String descripcion, Model model) {
        Categoria cate = new Categoria();
        cate.setTitulo(titulo);
        cate.setDescripcion(descripcion);
        System.out.println("sout:"+cate.getTitulo()+"/"+cate.getDescripcion());
        CategRepository.save(cate);
        return "redirect:/categorias";
    }
    @RequestMapping("/del/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
        Categoria cate = CategRepository.findById(id).orElse(null);
        CategRepository.delete(cate);
        return "redirect:/categorias";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("cate", CategRepository.findById(id).orElse(null));
        return "editCate";
    }
    @RequestMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String titulo, @RequestParam String descripcion) {
        Categoria cate = CategRepository.findById(id).orElse(null);
        cate.setTitulo(titulo);
        cate.setDescripcion(descripcion);
        CategRepository.save(cate);
        return "redirect:/categorias";
    }
}

