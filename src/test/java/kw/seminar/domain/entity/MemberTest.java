package kw.seminar.domain.entity;

import kw.seminar.repository.MemberJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
@Slf4j
class MemberTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @BeforeEach
    public void before() {
        memberJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("1차 캐시 테스트")
    @Transactional
    public void JPA_TEST_1() {

        //given
        Member member1 = new Member("팀");
        memberJpaRepository.save(member1);

        //when
        Optional<Member> member2 = memberJpaRepository.findMemberByName("팀");
        Optional<Member> member3 = memberJpaRepository.findMemberByName("팀");
        log.info("member1 = " + member1.toString());
        log.info("member2 = " + member2.get().toString());
        log.info("member3 = " + member3.get().toString());

        //then
        Assertions.assertThat(member3.get().getName()).isEqualTo(member2.get().getName());
    }
}