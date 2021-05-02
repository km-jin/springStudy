package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store=new HashMap<>();
    private static long sequence=0L;

    /* IntelliJ 단축키
    * alt+insert -> generate
    * ctrl+alt+m -> 메소드 추출하기
    * shift+F6 -> 같은 단어 찾고 바꿀수 있음음
   * */

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //get을 했을 때 pull일 수 있어서 Optional로 감싸고 return 한다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        /* 람다의 표현식
        * 매개변수 화살표(->) 함수 몸체로 이용하여 사용가능
        * (매개변수)->{함수몸체}
        * ()->{함수몸체}
        * (매개변수)->함수몸체
        * (매개변수)->{return 0;}
        * */
        //변수 store의 value는 Member 객체이기 때문에 다 돌면서 name과
        //같은 member가 있는지 확인
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //store의 모든 member를 List로 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
