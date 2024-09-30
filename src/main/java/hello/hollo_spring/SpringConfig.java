package hello.hollo_spring;

import hello.hollo_spring.repository.MemberRepository;
import hello.hollo_spring.repository.MemoryMemberRepository;
import hello.hollo_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    /**
     * 자바 코드로 직접 스프링빈 등록
     */
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
