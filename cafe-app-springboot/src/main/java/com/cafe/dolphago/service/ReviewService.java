package com.cafe.dolphago.service;

import com.cafe.dolphago.domain.cafe.Cafe;
import com.cafe.dolphago.domain.cafe.CafeRepository;
import com.cafe.dolphago.domain.review.Comment;
import com.cafe.dolphago.domain.review.CommentRepository;
import com.cafe.dolphago.domain.review.Review;
import com.cafe.dolphago.domain.review.ReviewRepository;
import com.cafe.dolphago.web.dto.review.CommentRequestDto;
import com.cafe.dolphago.web.dto.review.CommentUpdateRequestDto;
import com.cafe.dolphago.web.dto.review.ReviewResponseDto;
import com.cafe.dolphago.web.dto.review.ReviewUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public final ReviewRepository reviewRepository;
    public final CommentRepository commentRepository;
    public final CafeRepository cafeRepository;

    // [[[[[[[[[[[[ 리뷰 ]]]]]]]]]]]]]
    // 리뷰 저장
    @Transactional
    public void save(Review review) {
        reviewRepository.save(review);
    }

    // 리뷰 전체보기
    @Transactional
    public List<ReviewResponseDto> findByCcid(Long ccid) {
        List<ReviewResponseDto> list = new ArrayList<>();

        List<Review> temp = reviewRepository.findByCcid(ccid);

        for (Review rv : temp) {
            list.add(new ReviewResponseDto(rv));
        }

        return list;
    }

    // 리뷰 수정하기
    @Transactional
    public boolean reviewUpdate(Long rvid, String curuid, ReviewUpdateRequestDto reviewUpdateRequestDto) {
        // rvid
        Review review = reviewRepository.findById(rvid).get(); //엔티티 하나의 레코드를 가져옴
        String rvuid = review.getRvuid();//현재 리뷰작성자 아이디.
        if (rvuid.equals(curuid)) { //수정 권한이 있어
            review.update(reviewUpdateRequestDto.getRvcontent(), reviewUpdateRequestDto.getRvpic());
            return true;
        } else { //수정 권한이 없어
            return false;
        }
    }


    // 리뷰 삭제하기
    @Transactional
    public boolean reviewDelete(Long rvid, String curuid) {
        // rvid
        Review review = reviewRepository.findById(rvid).get(); //엔티티 하나의 레코드를 가져옴
        String rvuid = review.getRvuid();//현재 리뷰작성자 아이디.
        if (rvuid.equals(curuid)) { //삭제 권한이 있어
            reviewRepository.delete(review);
            return true;
        } else { //삭제 권한이 없어
            return false;
        }
    }


    // [[[[[[[[[[[[ 댓글 ]]]]]]]]]]]]]
    // comment저장
    @Transactional
    public boolean saveComment(CommentRequestDto commentRequestDto, Long rvid, String couid) {
        Review review = reviewRepository.findById(rvid).get(); //리뷰 객체를 가져왔어요.
        Cafe cafe=review.getCafereview();
        boolean flag=false;
        if(cafe.getUid().equals(couid)){
            commentRepository.save(commentRequestDto.toEntity(review,couid));
            flag=true;
        }
        return flag;
    }

    // 사장님 댓글 수정
    @Transactional
    public boolean commentUpdate(Long coid, String curuid, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment=commentRepository.findById(coid).get();  // comment정보 가지고오기

        // 카페 사장확인(현재 가입되어있는사람=카페사장)
        if (comment.getCouid().equals(curuid)) { //수정 권한이 있어
            comment.update(commentUpdateRequestDto.getComment());
            return true;
        } else { //수정 권한이 없어
            return false;
        }
    }

    // 사장님 댓글 삭제
    @Transactional
    public boolean commentDelete(Long coid, String curuid) {
        Comment comment=commentRepository.findById(coid).get();  // comment정보 가지고오기
        // 카페 사장확인(현재 가입되어있는사람=카페사장)
        if (comment.getCouid().equals(curuid)) { //삭제 권한이 있어
            commentRepository.delete(comment);
            return true;
        } else { //삭제 권한이 없어
            return false;
        }
    }
}


