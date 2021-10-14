/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ExamenParcial.controller;

import com.example.ExamenParcial.entity.Producto;
import com.example.ExamenParcial.repository.ProducRepository;
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
public class ProductoController {
    @Autowired
    private ProducRepository ProducRepository;
    @RequestMapping("/")
    public String mensaje(Model model){
        model.addAttribute("menaje", "Bienvenidos ");
        return "index";
    }
    @RequestMapping("/productos")
    public String produ(Model model){
        model.addAttribute("productos", ProducRepository.findAll());
        return "produ";
    }
    @RequestMapping("/form")
    public String create(Model model) {
        return "addProd";
    }
    @RequestMapping("/addProd")
    public String guardar(@RequestParam String titulo, @RequestParam String descripcion, Model model) {
        Producto produ = new Producto();
        produ.setTitulo(titulo);
        produ.setDescripcion(descripcion);
        System.out.println("sout:"+produ.getTitulo()+"/"+produ.getDescripcion());
        ProducRepository.save(produ);
        return "redirect:/productos";
    }
    @RequestMapping("/del/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
        Producto produ = ProducRepository.findById(id).orElse(null);
        ProducRepository.delete(produ);
        return "redirect:/productos";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("produ", ProducRepository.findById(id).orElse(null));
        return "editProd";
    }
    @RequestMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String titulo, @RequestParam String descripcion) {
        Producto produ = ProducRepository.findById(id).orElse(null);
        produ.setTitulo(titulo);
        produ.setDescripcion(descripcion);
        ProducRepository.save(produ);
        return "redirect:/productos";
    }
}

