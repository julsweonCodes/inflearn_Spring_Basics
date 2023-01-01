package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // <- 자동으로 스프링이 뜰 때 객체가 생성되어 컨테이너에 넣어짐
public class MemberController {

    /*
    여러 개의 인스턴스를 new로 생성하는 건 안 좋음(별 기능 없을 떄)
    ㄴ 하나의 인스턴스 만들어 놓고 공용으로 쓰면 됨 -> spring 컨테이너에 등록하고 사용
     */

    private final MemberService memberService;

    @Autowired
    // MemberService가 생성 될 떄, 스프링 컨테이너 안(스프링 빈에 등록된)의 memberService 객체와 자동으로 연결시켜 줌
    // ㄴ DI(Dependency Injection)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
