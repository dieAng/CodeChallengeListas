package com.example.codechange.models.suma;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "sumas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Suma {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String referencia;
    private Date fecha;
    private Integer suma;
    private String comentario;

    public Suma(SumaDto sumaDTO) {
        this.fecha = sumaDTO.getFecha();
        this.suma = sumaDTO.getSuma();
        this.comentario = sumaDTO.getComentario();
    }
}
