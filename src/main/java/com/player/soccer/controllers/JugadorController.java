package com.player.soccer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.player.soccer.models.Equipo;
import com.player.soccer.models.Jugador;


@Controller
public class JugadorController {

    // Action que recibira por PATH los parametros a buscar
    @GetMapping("/{nombre}/{numero}")
    public String searchPlayer(@PathVariable String nombre, @PathVariable("numero") Integer numero, Model model) {
        Optional<Equipo> equipoOptional = getEquipo().stream()
                .filter(equipo -> nombre.toLowerCase().equals(equipo.getNombre().toLowerCase())).findFirst();

        if (equipoOptional.isPresent()) {
            Optional<Jugador> jugadorOptional = equipoOptional.get().getPlantilla().stream()
                    .filter(jugador -> numero == jugador.getNumero()).findFirst();
            
            if (jugadorOptional.isPresent()) {
                model.addAttribute("titulo", "Busca tu jugador");
                model.addAttribute("jugador", jugadorOptional.get());
            }
        }
        return "index";
    }

    // Metodo que retornara lista de equipos
    private List<Equipo> getEquipo() {

        // Instanciamos
        Equipo nacional = new Equipo();
        nacional.setNombre("Nacional");
        nacional.addJugador(new Jugador("Samuel Velásquez", 33));
        nacional.addJugador(new Jugador("Juan Jose Arias", 36));
        nacional.addJugador(new Jugador("Cristian Castro", 42));
        nacional.addJugador(new Jugador("Andres Salazar", 4));
        nacional.addJugador(new Jugador("Jader", 11));

        Equipo cali = new Equipo();
        cali.setNombre("Cali");
        cali.addJugador(new Jugador("Adrian Palacios", 28));
        cali.addJugador(new Jugador("Kevin Saucedo", 31));
        cali.addJugador(new Jugador("Fabry Castro Barros", 18));
        cali.addJugador(new Jugador("Onel Acosta", 1));
        cali.addJugador(new Jugador("Enrique Camargo", 6));

        return List.of(nacional, cali);

    }

}
