package model.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Issue;
import java.util.List;

@Path("/issues")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class IssueResource {
    @PersistenceContext(unitName = "CRUD")
    private EntityManager em;

    @GET
    public List<Issue> listar() {
        return em.createQuery("SELECT i FROM Issue i", Issue.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Issue i = em.find(Issue.class, id);
        if (i == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(i).build();
    }

    @POST
    @Transactional
    public Response crear(Issue i, @Context UriInfo uriInfo) {
        em.persist(i);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(i.getId().toString());
        return Response.created(builder.build()).entity(i).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response editar(@PathParam("id") Long id, Issue datos) {
        Issue existente = em.find(Issue.class, id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existente.setPublishDate(datos.getPublishDate());
        existente.setUnpublishDate(datos.getUnpublishDate());
        existente.setManageDate(datos.getManageDate());
        existente.setIssueStatus(datos.getIssueStatus());
        em.merge(existente);
        return Response.ok(existente).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        Issue i = em.find(Issue.class, id);
        if (i == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(i);
        return Response.noContent().build();
    }
} 