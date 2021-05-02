package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //ctrl+shift+T -> create new test 자동으로 만들어줌!
    private final MemoryMemberRepository memberRepository;
    //같은 인스턴스로 test해야 하기 때문에 new MemoryMemberRepository로 하는 것이 아니라
    //constructor로 만들어서 사용

    //alt+insert -> Generate constructor 한다.
    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 회원 가입*/
    public Long join(Member member){
        //같은 이름이 있는 중복 회원x
        //ctrl+alt+v (Extract->Variable) 변수 추출하기
        validateDuplicateMember(member); //중복 회원 검증
        /* ifPresent
        * -> Optional 제공, if문을 줄일수 있다. if(result !=null)과 같은 의미
        * */

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //ctrl+alt+shift+T -> Extract Method 하면 메소드가 따로 분리된다.
        //ctrl+alt+M으로 하면 바로 분리됨!
        memberRepository.findByName(member.getName())
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /** 전체회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
