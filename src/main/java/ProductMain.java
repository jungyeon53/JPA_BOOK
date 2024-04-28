import entity.Member;
import entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProductMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");

        em.persist(productA);

        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        member1.getProducts().add(productA);
        em.persist(member1);
        tx.commit();

        // 조회
        find(em);
        findInverse(em);
    }
    public static void find(EntityManager em){
        // 탐색
        Member member = em.find(Member.class, "member1");
        List<Product> products = member.getProducts();
        for(Product product : products){
            System.out.println(product.getName());
        }
    }

    public static void findInverse(EntityManager em){
        Product product = em.find(Product.class, "productA");
        List<Member> members = product.getMembers();
        for(Member member : members){
            System.out.println(member.getUsername());
        }
    }

}
