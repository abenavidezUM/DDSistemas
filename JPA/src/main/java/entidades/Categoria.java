package entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity

public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;


    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private List<Articulo> articulos = new ArrayList<>();
}
