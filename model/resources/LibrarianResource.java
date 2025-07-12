package model.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Librarian;
import java.util.List;

@Path("/librarians")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class LibrarianResource {
    @PersistenceContext(unitName = "CRUD")
    private EntityManager em;

    @GET
    public List<Librarian> listar() {
        return em.createQuery("SELECT l FROM Librarian l", Librarian.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Librarian l = em.find(Librarian.class, id);
        if (l == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(l).build();
    }

    @POST
    @Transactional
    public Response crear(Librarian l, @Context UriInfo uriInfo) {
        em.persist(l);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(l.getId().toString());
        return Response.created(builder.build()).entity(l).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response editar(@PathParam("id") Long id, Librarian datos) {
        Librarian existente = em.find(Librarian.class, id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existente.setIdLibrarian(datos.getIdLibrarian());
        em.merge(existente);
        return Response.ok(existente).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        Librarian l = em.find(Librarian.class, id);
        if (l == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(l);
        return Response.noContent().build();
    }
} 