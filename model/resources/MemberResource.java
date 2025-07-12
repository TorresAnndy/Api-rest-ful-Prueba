package model.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Member;
import java.util.List;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class MemberResource {
    @PersistenceContext(unitName = "CRUD")
    private EntityManager em;

    @GET
    public List<Member> listar() {
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Member m = em.find(Member.class, id);
        if (m == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(m).build();
    }

    @POST
    @Transactional
    public Response crear(Member m, @Context UriInfo uriInfo) {
        em.persist(m);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(m.getId().toString());
        return Response.created(builder.build()).entity(m).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response editar(@PathParam("id") Long id, Member datos) {
        Member existente = em.find(Member.class, id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existente.setIdMember(datos.getIdMember());
        em.merge(existente);
        return Response.ok(existente).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        Member m = em.find(Member.class, id);
        if (m == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.remove(m);
        return Response.noContent().build();
    }
} 