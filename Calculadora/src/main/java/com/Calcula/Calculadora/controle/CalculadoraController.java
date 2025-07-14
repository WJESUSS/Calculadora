package com.Calcula.Calculadora.controle;


import com.Calcula.Calculadora.modelo.Operacao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculadoraController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("operacao", new Operacao());
        return "index";
    }

    @PostMapping("/calcular")
    public String calcular(@ModelAttribute Operacao operacao, Model model) {
        double resultado = 0.0;

        switch (operacao.getOperacao()) {
            case "soma":
                resultado = operacao.getNumero1() + operacao.getNumero2();
                break;
            case "subtracao":
                resultado = operacao.getNumero1() - operacao.getNumero2();
                break;
            case "multiplicacao":
                resultado = operacao.getNumero1() * operacao.getNumero2();
                break;
            case "divisao":
                if (operacao.getNumero2() != 0) {
                    resultado = operacao.getNumero1() / operacao.getNumero2();
                } else {
                    model.addAttribute("erro", "Divisão por zero não é permitida!");
                    return "index";
                }
                break;
        }

        operacao.setResultado(resultado);
        model.addAttribute("operacao", operacao);
        return "index";
    }
}
