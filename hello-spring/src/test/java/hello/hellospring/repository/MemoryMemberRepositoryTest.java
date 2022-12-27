package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

//Test는 여러 개를 동시에 돌릴 수 있다
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach         // test 하나 끝날 때마다 실행하는 함수
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //저장하고

        Member result = repository.findById(member.getId()).get(); //return 타입 - Optional
        //System.out.println("result= " + (result == member));

        //Assertions - junit
        //Assertions.assertEquals(member, result);
        //Assertions.assertEquals(member, null);

        //Assertions - assertj.core
        Assertions.assertThat(member).isEqualTo(result); //member가 result와 같은가?

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //Optional<Member> result = repository.findByName("spring1");
        Member result = repository.findByName("spring1").get(); //get 사용하면 optional 까서 꺼내기 가능

        //Assertions.assertThat(result).isEqualTo(member2);
        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }



}
