package hello.hollo_spring.service;

import hello.hollo_spring.domain.Member;
import hello.hollo_spring.repository.MemberRepository;
import hello.hollo_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public long join(Member member){
        long start = System.currentTimeMillis();
        try{
            // 중복 회원 X
            // ifPresent NULL 값이 아닌 값을 가지고있으면 true
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
            
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findById(memberId);
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findOne = " + timeMs + "ms");
        }
    }

}
