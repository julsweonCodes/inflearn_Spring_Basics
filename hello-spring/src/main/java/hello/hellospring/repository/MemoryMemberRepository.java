package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //동시성 문제로 실무에서는 다르게 처리하지만 간단한 예제이므로 이렇게 설정


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //loop 돌리기
                .filter(member -> member.getName().equals(name)) //store에 저장된 것들 중에 name이 파라미터로 넘어온 name과 같은 것을 찾는것
                .findAny(); //같은 게 있으면 찾는 것(Optional로 반환됨)
    }

    @Override
    public List<Member> findAll() { //Map인데 List로 반환됨
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
