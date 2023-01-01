package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
// git check
class MemberServiceTest {

    /*
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    */

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { //DI - dependency injection
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // given - when - then 주석 사용하기
    @Test
    void 회원가입() {
        //given - 기반 데이터
        Member member = new Member();
        member.setName("spring");

        //when - 검증하는 부분
        Long saveId = memberService.join(member);

        //then - 검증부
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage())
                    .isEqualTo("Member with same name already exists.");
        }
        */

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //, 이하를 실행하면 , 이전이 터져야 함
        Assertions.assertThat(e.getMessage()).isEqualTo("Member with same name already exists.");
        //then

    }


    @Test
    void 전체회원조회() {
    }

    @Test
    void 회원조회() {
    }
}