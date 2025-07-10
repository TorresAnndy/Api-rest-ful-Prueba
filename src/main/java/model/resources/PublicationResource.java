package model.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Publication;
import java.util.List;

@Path("/publications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PublicationResource {
    @PersistenceContext(unitName = "CRUD")
    private EntityManager em;

    @GET
    public List<Publication> listar() {
        return em.createQuery("SELECT p FROM Publication p", Publication.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Publication pub = em.find(Publication.class, id);
        if (pub == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pub).build();
    }

    @POST
    @Transactional
    public Response crear(Publication pub, @Context UriInfo uriInfo) {
        em.persist(pub);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(pub.getId().toString());
        return Response.created(builder.build()).entity(pub).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response editar(@PathParam("id") Long id, Publication datos) {
        Publication existente = em.find(Publication.class, id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existente.setTitle(datos.getTitle());
        existente.setYear(datos.getYear());
        existente.setAuthor(datos.getAuthor());
        existente.setStatusPublication(datos.getStatusPublication());
        em.merge(existente);
        return Response.ok(existente).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        Publication pub = em.find(Publication.class, id);
        if (pub == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(pub);
        return Response.noContent().build();
    }
} 