package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //실행하기 전에 memoryMemberRepository()를 새로 생성해서
    //memberService로 넣어주면 같은 memoryMemberRepository를 사용할수 있다.
    //memberService를 새로 생성하는 것이 아니라 외부에서 값을 넣어주기 때문에
    //이런식으로 하는 것을 Dependency Injection(DI, 의존성 주입)이라고 한다.
    @BeforeEach
    public void beforeEach(){
        memberRepository =new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    //shift+F10 -> 이전 실행한 것을 다시 실행해줌
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        /* test문법! */
        //given
        Member member=new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        //alt+enter누르고 static import로 변경하여 설정할수 있다.
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
 /*     아래 내용을 바꾸면 위처럼 이렇게 됨!
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}