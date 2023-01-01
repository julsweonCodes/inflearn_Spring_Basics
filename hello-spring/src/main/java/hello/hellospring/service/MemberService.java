package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService { //서비스 - 비즈니스 로직 구현

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // MemberService는 MemberRepository가 필요함 -> 생성될 때 autowired로 연결
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름의 중복 회원X

        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        //Optional로 감싸면 Optional의 여러 내장 함수를 사용 가능, 안에 내용 꺼내고 싶으면 get()/orElseGet 사용하면 됨
        result.ifPresent(m -> {
            throw new IllegalStateException("Member with same name already exists.");
        });
         */

        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("Member with same name already exists.");
                        });
    }

    /**
     * 
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return  memberRepository.findAll();
    }

    /**
     * 회원 ID로 찾기
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
