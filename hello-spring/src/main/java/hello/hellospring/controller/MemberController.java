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

    /*
    <DI>
     #1. 필드 주입
     스프링 뜰 때만 넣어줌, 중간에 못 바꿈, 권장x
     @Autowired private MemberService memberService;


     #2. Setter 주입
     누군가가 memberController를 호출했을 때 메서드가 public으로 열려 있어야만 한다
     -> 로딩 시점 이후 중간에 변경됐을 경우 문제 발생 가능
     private MemberService memberService;

     @Autowired
     public void setMemberService(MemberService memberService) {
         this.memberService = memberService;
    }


    #3. 생성자 이용 주입 (조립)
     *위에서 사용한 방법 - 가장 권장하는 방법.
     의존 관계가 실행 중 동적으로 변하는 경우는 거의 없음 (i.e. memberService -> memberRepository)

     ** 프로젝트에서 아직 DB가 선정되지 않아 MemberRepository 인터페이스를 사용하는 것으로 가정
        후에 나머지 코드를 건들지 않고 SpringConfig 파일의 구현 클래스 코드 수정만으로 쉽게 변경할 수 있게 하기 위함.
        -> SpringConfig.java에서 MemberRepository에서 return DBMemberReposity()를 반환하는 것으로..
     */

}
