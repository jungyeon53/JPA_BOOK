import entity.Member;
import entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin(); // 트랜잭션 시작
            addMember(em);
            addTeam(em);

            tx.commit();

            biDirection(em);

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
    // 비지니스 로직
    private static void addMember (EntityManager em){
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("정연");
        member.setAge(29);

        // 등록
        em.persist(member);
        // 수정
        member.setAge(27);
        // 1개 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember:" + findMember.getUsername() + "age:" + findMember.getAge());
        // 리스트 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members: " + members.size());

        em.remove(member);
    };

    public static void biDirection(EntityManager em){
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers();
        System.out.println("시작" + members);
        for(Member member : members){
            System.out.println("member.username" + member.getUsername());
        }
    }

    public static void addTeam(EntityManager em){
        // 팀 1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);
        System.out.println();

        // 회원 1 저장
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        // 회원 2 저장
        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

        addTeamMember(em, member1, member2, team1);
    }
    public static void addTeamMember(EntityManager em, Member member1, Member member2, Team team1){
        Team team2 = new Team();
        team2.getMembers().add(member1);
        System.out.println("팀2" + team2.getMembers());
        for(Member member : team1.getMembers()){
            System.out.println(member.getUsername());
        }
        System.out.println("멤버1" + member1.getUsername());
    }
}
