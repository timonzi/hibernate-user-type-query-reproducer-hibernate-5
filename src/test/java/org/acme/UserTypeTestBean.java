package org.acme;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserTypeTestBean {

    @Inject
    EntityManager em;


    @Transactional
    void createEntity() {
        final var entity = new MyEntity();
        entity.setField(new MyStringWrapper("some value"));
        em.persist(entity);
    }

    @Transactional
    List<MyEntity> getByQuery() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MyEntity> query = cb.createQuery(MyEntity.class);
        Root<MyEntity> root = query.from(MyEntity.class);

        query.where(
                cb.equal(cb.upper(root.get("field")), new MyStringWrapper("SOME VALUE"))
        );
        TypedQuery<MyEntity> result = em.createQuery(query);
        return result.getResultList();
    }

}
