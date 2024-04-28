import entity.Member;
import entity.Orders;
import entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OrderMain {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();

            // 회원저장
            Member member1 = new Member();
            member1.setId("member1");
            member1.setUsername("회워1");
            em.persist(member1);

            // 상품저장
            Product productA = new Product();
            productA.setId("productA");
            productA.setName("상품1");
            em.persist(productA);

            // 주문저장
            Orders orders = new Orders();
            orders.setMember(member1);
            orders.setProduct(productA);
            orders.setOrderAmount(2);
            em.persist(orders);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }

        Long orderId = 1L;
        Orders orders = em.find(Orders.class, orderId);
        Member member = orders.getMember();
        Product product = orders.getProduct();
        System.out.println(member.getUsername());
        System.out.println(orders.getMember().getUsername());
        System.out.println(orders.getOrderAmount());
    }
}
