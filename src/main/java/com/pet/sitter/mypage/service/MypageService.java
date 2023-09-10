package com.pet.sitter.mypage.service;

import com.pet.sitter.common.entity.Matching;
import com.pet.sitter.common.entity.Member;
import com.pet.sitter.common.entity.Petsitter;
import com.pet.sitter.common.entity.Question;
import com.pet.sitter.exception.DataNotFoundException;
import com.pet.sitter.mainboard.dto.PetSitterDTO;
import com.pet.sitter.member.dto.MemberDTO;
import com.pet.sitter.mypage.dto.MatchingDTO;
import com.pet.sitter.mypage.repository.MypageRepository;

import com.pet.sitter.mypage.repository.MypageRepository2;
import com.pet.sitter.mypage.repository.MypageRepository3;
import com.pet.sitter.mypage.repository.MypageRepository4;
import com.pet.sitter.qna.dto.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final MypageRepository mypageRepository;
    private final MypageRepository2 mypageRepository2;
    private final MypageRepository3 mypageRepository3;
    private final MypageRepository4 mypageRepository4;
    private final PasswordEncoder passwordEncoder;



    //매칭내역가져오기
    /*
    public Page<MatchingDTO> getMatchingList(long id, int page) {
        List<Sort.Order> sorts = new ArrayList();
        sorts.add(Sort.Order.desc("petsitter.sitterNo")); //내림차순기준
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        Page<Matching> matchingPage = mypageRepository4.findByMatching(id,pageable);//페이지처리하기위한 Page<Matching>가져오기
        System.out.printf("여기는 레파지토리 에서 가져온 매칭갯수%d",matchingPage.getTotalElements());

        Page<MatchingDTO> matchingDTOPage = matchingPage.map(matching -> {   //member1의 matchingDTO가져오기
            MatchingDTO dto = new MatchingDTO(matching);

            dto.setMember(new MemberDTO(matching.getMember()));
            dto.setMember2(new MemberDTO(matching.getMember2()));

            return dto;
        });
        return matchingDTOPage;
    }


    //매칭글 가져오기
    public Page<PetSitterDTO> getMatchingArticleList(long id , int page){
        List<Sort.Order> sorts = new ArrayList();
        sorts.add(Sort.Order.desc("sitterNo")); //내림차순기준
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        Page<Petsitter> petsitterPage = mypageRepository2.findByMatchingArticle(id,pageable);//page<Petsitter>를 page<PetSitterDTO>로 변환
        System.out.printf("여기는 레파지토리 에서 가져온 펫시터 엔티티갯수%d",petsitterPage.getTotalElements());

        Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> {
            PetSitterDTO dto = new PetSitterDTO(petsitter);
            dto.setMember(new MemberDTO(petsitter.getMember())); // Member 설정
            return dto;
        });

         return petSitterDTOPage;

        Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> new PetSitterDTO(petsitter));

        return petSitterDTOPage;


    }

    */







    /*//jointest
    public Page<PetSitterDTO> jointest(long id , int page){
        List<Sort.Order> sorts = new ArrayList();
        sorts.add(Sort.Order.desc("petRegdate")); //내림차순기준
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        Page<Petsitter> petsitterPage = mypageRepository2.findBytest(id,pageable);//page<Petsitter>를 page<PetSitterDTO>로 변환
        System.out.printf("여기는 레파지토리 에서 가져온 펫시터 엔티티갯수%d",petsitterPage.getTotalElements());

        Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> {
            PetSitterDTO dto = new PetSitterDTO(petsitter);
            dto.setMember(new MemberDTO(petsitter.getMember())); // Member 설정
            return dto;
        });

        *//*return petSitterDTOPage;

        Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> new PetSitterDTO(petsitter));*//*

        return petSitterDTOPage;


    }*/



    //내정보보기
    public MemberDTO getMember(String memberId) {
        Optional<Member> member = mypageRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            MemberDTO memberDTO = new MemberDTO(member.get());
            return memberDTO;
        }else{
            throw new DataNotFoundException("member not Found");
        }
    }

    //내정보보기
    public Member getMemberEntity(String memberId) {
        Optional<Member> member = mypageRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            return member.get();

        }else{
            throw new DataNotFoundException("member not Found");
        }
    }

    /*public Page<Petsitter> getMyArticleList(String id , int page){
        List<Sort.Order> sorts = new ArrayList();
        sorts.add(Sort.Order.desc("petRegdate"));
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        Page<Petsitter> petsitterPage = mypageRepository2.findByMemberMemberId(id,pageable);
        Page<PetSitterDTO> petSitterDTOPage;
        for(int i=0;i<=petsitterPage.getTotalElements();i++){

        }

        return mypageRepository2.findByMemberMemberId(id,pageable);
    }*/

    //내가쓴글보기
    public Page<PetSitterDTO> getMyArticleList(String id , int page){
        List<Sort.Order> sorts = new ArrayList();
        sorts.add(Sort.Order.desc("petRegdate")); //내림차순기준
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        Page<Petsitter> petsitterPage = mypageRepository2.findByMemberMemberId(id,pageable);//page<Petsitter>를 page<PetSitterDTO>로 변환
        Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> {
            PetSitterDTO dto = new PetSitterDTO(petsitter);
            dto.setMember(new MemberDTO(petsitter.getMember())); // Member 설정
            return dto;
        });

        return petSitterDTOPage;

        /*Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> new PetSitterDTO(petsitter));

        return petSitterDTOPage;
*/

    }

    //나의 QnA
    public Page<QuestionDTO> getMyQnAList(String id , int page){
        System.out.println("여기는 서비스 getMyQnAList");
        List<Sort.Order> sorts = new ArrayList();
        sorts.add(Sort.Order.desc("qnaDate")); //내림차순기준
        System.out.println("여기는 서비스 getMyQnAList2");
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        Page<Question> questionPage = mypageRepository3.findByMemberMemberId(id,pageable);//page<Petsitter>를 page<PetSitterDTO>로 변환
        Page<QuestionDTO> questionDTOPage = questionPage.map(question -> {
            QuestionDTO dto = new QuestionDTO();
            dto.setMember(question.getMember()); // Member 설정
            dto.setQnaNo(question.getQnaNo());
            dto.setQnaTitle(question.getQnaTitle());
            dto.setQnaDate(question.getQnaDate());
            System.out.println("여기는 service의 getMyQnaList 현재 아이디"+question.getQnaDate());
            return dto;
        });

        return questionDTOPage;

        /*Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> new PetSitterDTO(petsitter));

        return petSitterDTOPage;
*/

    }






/*    //나의 매칭내역보기
    public Page<PetSitterDTO> getMyMatchingList(String id , int page){
        List<Sort.Order> sorts = new ArrayList();
        sorts.add(Sort.Order.desc("petRegdate")); //내림차순기준
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        Page<Petsitter> petsitterPage = mypageRepository2.findByMatchingMemberId(id,pageable);//page<Petsitter>를 page<PetSitterDTO>로 변환
        Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> {
            PetSitterDTO dto = new PetSitterDTO(petsitter);
            dto.setMember(petsitter.getMember()); // Member 초기화
            return dto;
        });

        return petSitterDTOPage;

        *//*Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> new PetSitterDTO(petsitter));

        return petSitterDTOPage;
*//*

    }*/



    //상세보기

    public PetSitterDTO getPetsitter(Long id){
        Optional<Petsitter> petsitter = mypageRepository2.findById(id);
        if(petsitter.isPresent()){
            PetSitterDTO petsitterDTO = new PetSitterDTO(petsitter.get());
            petsitterDTO.setMember(new MemberDTO(petsitter.get().getMember()));
            return petsitterDTO;
        }else{
            throw new DataNotFoundException("Question not Found");
        }
    }

    //비번수정
    public void passModify(Member member,String password1){
        member.setPw(passwordEncoder.encode(password1));
        mypageRepository.save(member);
    }

    //이름 주소 폰번호 수정 하고 저장
    public void infoModify(Member member, String address, String name, String phone,String nickname) {
        member.setAddress(address);
        member.setName(name);
        member.setPhone(phone);
        member.setNickname(nickname);
        mypageRepository.save(member);
    }


}

 /*Page<PetSitterDTO> petSitterDTOPage = petsitterPage.map(petsitter -> {
            PetSitterDTO dto = new PetSitterDTO();
            dto.setMember(petsitter.getMember());

            return dto;
        });*/

//return petSitterDTOPage;

//return mypageRepository2.findByMemberMemberId(id,pageable);