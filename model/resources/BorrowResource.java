package model.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Borrow;
import java.util.List;
import model.Member;
import model.Publication;

@Path("/borrows")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BorrowResource {
    @PersistenceContext(unitName = "CRUD")
    private EntityManager em;

    @GET
    public List<Borrow> listar() {
        return em.createQuery("SELECT b FROM Borrow b", Borrow.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Borrow b = em.find(Borrow.class, id);
        if (b == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(b).build();
    }

    @POST
    @Transactional
    public Response crear(Borrow b, @QueryParam("memberId") Long memberId, @QueryParam("publicationId") Long publicationId, @Context UriInfo uriInfo) {
        if (memberId != null) {
            Member member = em.find(Member.class, memberId);
            if (member == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Member not found").build();
            }
            b.setMember(member);
        }
        if (publicationId != null) {
            Publication publication = em.find(Publication.class, publicationId);
            if (publication == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Publication not found").build();
            }
            b.setPublication(publication);
        }
        em.persist(b);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(b.getId().toString());
        return Response.created(builder.build()).entity(b).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response editar(@PathParam("id") Long id, Borrow datos, @QueryParam("memberId") Long memberId, @QueryParam("publicationId") Long publicationId) {
        Borrow existente = em.find(Borrow.class, id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existente.setFromDate(datos.getFromDate());
        existente.setToDate(datos.getToDate());
        existente.setBorrowStatus(datos.getBorrowStatus());
        if (memberId != null) {
            Member member = em.find(Member.class, memberId);
            if (member == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Member not found").build();
            }
            existente.setMember(member);
        }
        if (publicationId != null) {
            Publication publication = em.find(Publication.class, publicationId);
            if (publication == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Publication not found").build();
            }
            existente.setPublication(publication);
        }
        em.merge(existente);
        return Response.ok(existente).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        Borrow b = em.find(Borrow.class, id);
        if (b == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(b);
        return Response.noContent().build();
    }
} 