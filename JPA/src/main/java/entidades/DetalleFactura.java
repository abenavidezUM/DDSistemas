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

public class DetalleFactura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private int subTotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_factura")
    private Factura factura;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;
}
