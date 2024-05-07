import entity.Parent;
import entity.ParentId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ParentMain {

    public static void main(String[] args) {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin(); // 트랜잭션 시작

            Parent parent = new Parent();
//            parent.setId1("myId1");
//            parent.setId2("myId2");
            parent.setName("parentName");
            em.persist(parent);
            tx.commit();

            ParentId parentId = new ParentId("myId1", "myId2");
            em.find(Parent.class, parentId);
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
