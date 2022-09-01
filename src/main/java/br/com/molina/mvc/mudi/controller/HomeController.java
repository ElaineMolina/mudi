package br.com.molina.mvc.mudi.controller;

import br.com.molina.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.molina.mvc.mudi.model.Pedido;
import br.com.molina.mvc.mudi.model.StatusPedido;
import br.com.molina.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private PedidoRepository pedidoRepository;
    @GetMapping
    public String home(Model model){
            List<Pedido> pedidos = pedidoRepository.findAll();
            model.addAttribute("pedidos", pedidos);
        return "home";
    }
    @GetMapping("/{status}")
    public String porStatus(@PathVariable("status") String status, Model model){
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/home";
    }


}
