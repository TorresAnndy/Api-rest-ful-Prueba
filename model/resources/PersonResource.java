package model.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Person;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PersonResource {
    @PersistenceContext(unitName = "CRUD")
    private EntityManager em;

    @GET
    public List<Person> listar() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Person p = em.find(Person.class, id);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(p).build();
    }

    @POST
    @Transactional
    public Response crear(Person p, @Context UriInfo uriInfo) {
        em.persist(p);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(p.getId().toString());
        return Response.created(builder.build()).entity(p).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response editar(@PathParam("id") Long id, Person datos) {
        Person existente = em.find(Person.class, id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existente.setName(datos.getName());
        existente.setSurname(datos.getSurname());
        existente.setAddress(datos.getAddress());
        existente.setAge(datos.getAge());
        em.merge(existente);
        return Response.ok(existente).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        Person p = em.find(Person.class, id);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(p);
        return Response.noContent().build();
    }
} 