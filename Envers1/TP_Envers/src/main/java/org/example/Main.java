package org.example;

import Entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Factura factura1 = Factura.builder()
                    .fecha("11/09/2024")
                    .numero(12)
                    .total(120)
                    .build();

            Domicilio dom = Domicilio.builder()
                    .nombreCalle("San Martin")
                    .numero(123)
                    .build();

            Cliente cliente1 = Cliente.builder()
                    .nombre("Camila")
                    .apellido("Portal")
                    .dni(44908542)
                    .build();

            cliente1.setDomicilio(dom);

            Categoria perecederos = Categoria.builder()
                    .denominacion("Perecederos")
                    .build();

            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();

            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            Articulo art1 = Articulo.builder()
                    .cantidad(200)
                    .denominacion("Yogurt")
                    .precio(20)
                    .build();

            Articulo art2 = Articulo.builder()
                    .cantidad(300)
                    .denominacion("Detergente Magistral")
                    .precio(80)
                    .build();

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);

            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            DetalleFactura det1 = DetalleFactura.builder()
                    .cantidad(2)
                    .subTotal(40)
                    .build();

            det1.setArticulo(art1);

            factura1.setCliente(cliente1);
            factura1.getDetalles().add(det1);

            DetalleFactura det2 = DetalleFactura.builder()
                    .cantidad(1)
                    .subTotal(80)
                    .build();

            det2.setArticulo(art2);
            factura1.getDetalles().add(det2);

            em.persist(factura1);
            em.flush();
            em.getTransaction().commit();


        }catch (Exception e){
            em.getTransaction().rollback();
        }

        em.close();
        emf.close();

    }
}