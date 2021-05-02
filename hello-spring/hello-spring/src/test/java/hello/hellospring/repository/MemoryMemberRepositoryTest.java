package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        //각각의 메소드실행이 끝날때마다 호출이됨.
        //설정안해주면 영향을 받아서 오류뜸
        repository.clearStore();
        //리포지토리에 저장된 값들(store)을 clear해줌
    }

    @Test
    public void save(){
        Member member=new Member();
        member.setName("spring");

        repository.save(member);

        Member result=repository.findById(member.getId()).get();
        //get()을 통해 Optional에서 Member를 뽑아냄

        //System.out.println("result = "+(result==member));
        //Assertions.assertEquals(member, result); //(기대값, 실제값)
        //test 값이 다르면 경고가뜨면서 다르다고알려줌, 값이 같다면 파란불!
        Assertions.assertThat(member).isEqualTo(result);
        //member(기대값) , result(실제값)
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift+F6 같은 이름의 변수들을 한번에 바꿔준다.
        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
