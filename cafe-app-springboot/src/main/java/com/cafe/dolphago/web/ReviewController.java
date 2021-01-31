package com.cafe.dolphago.web;

import com.cafe.dolphago.domain.cafe.Cafe;
import com.cafe.dolphago.service.CafeService;
import com.cafe.dolphago.service.ReviewService;
import com.cafe.dolphago.service.jwt.JwtService;
import com.cafe.dolphago.service.jwt.UnauthorizedException;
import com.cafe.dolphago.web.dto.review.*;
import com.cafe.dolphago.web.dto.user.UserJwtResponsetDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtService jwtService;
    private final CafeService cafeService;


    // [[[[[[[[[[[[ 리뷰 ]]]]]]]]]]]]]
    // 리뷰 저장
    // ccid, uid-> token
    @ApiOperation("카페마다 손님들의 리뷰를 저장하는 기능")
    @PostMapping("/latte/review/create/{ccid}")
    public Map save(@PathVariable Long ccid, HttpServletRequest httpServletRequest, @RequestBody ReviewSaveRequestDto reviewSaveRequestDto) {
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);

        Cafe cafe=cafeService.findByCcId(ccid);
        reviewService.save(reviewSaveRequestDto.toEntity(cafe,user.getUid()));

        Map<String,String> map=new HashMap<>();
        map.put("result","리뷰가 저장되었습니다~");
        return map;
    }

    // 리뷰 전체 보기
    @ApiOperation("리뷰 전체 보기")
    @GetMapping("/latte/review/{ccid}")
    public List<ReviewResponseDto> selectAll(@PathVariable Long ccid) {
        return reviewService.findByCcid(ccid);
    }


    // 리뷰 수정
    // 토큰의 uid와 rvuid가 동일하다면, 수정하러 들어간다
    @ApiOperation("리뷰 수정 하기")
    @PutMapping("/latte/review/update/{rvid}")  // rvuid
    public Map reviewUpdate(HttpServletRequest httpServletRequest, @RequestBody ReviewUpdateRequestDto reviewUpdateRequestDto, @PathVariable Long rvid) {
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);
        Map<String,String> map=new HashMap<>();
        boolean state=reviewService.reviewUpdate(rvid,user.getUid(),reviewUpdateRequestDto);
        if(state){
            map.put("result","리뷰가 수정되었습니다~");
        }else{
            map.put("result","수정중 오류가 발생했습니다.");
        }
        return map;
    }

    // 리뷰 삭제
    // 토큰의 uid와 rvuid가 동일하다면, 해당 rvid를 삭제한다
    @ApiOperation("리뷰 삭제 하기")
    @DeleteMapping("/latte/review/delete/{rvid}")
    public Map reviewDelete(HttpServletRequest httpServletRequest,@PathVariable Long rvid) {
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);

        Map<String,String> map=new HashMap<>();
        boolean state=reviewService.reviewDelete(rvid,user.getUid());
        if(state){
            map.put("result","리뷰가 삭제되었습니다~");
        }else{
            map.put("result","삭제중 오류가 발생했습니다.");
        }
        return map;
    }


    // [[[[[[[[[[[[ 댓글 ]]]]]]]]]]]]]
    // 사장님 댓글 작성   -> 사장님인지 확인(토큰)
    @ApiOperation("사장님 댓글 작성(comment)")
    @PostMapping("/latte/comment/create/{rvid}")
    public Map saveComment(HttpServletRequest httpServletRequest,@PathVariable Long rvid, @RequestBody CommentRequestDto commentRequestDto) {
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);
        Map<String,String> map=new HashMap<>();

        if(reviewService.saveComment(commentRequestDto, rvid,user.getUid())){
            map.put("result","저장이 완료되었습니다~");
        }else map.put("result","저장에 실패하였습니다.");
        return map;
    }


    // 사장님 댓글 수정
    @ApiOperation("사장님 댓글 수정 하기")
    @PutMapping("/latte/comment/update/{coid}")  // rvuid
    public Map commentUpdate(HttpServletRequest httpServletRequest, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto, @PathVariable Long coid) {
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);
        Map<String,String> map=new HashMap<>();
        boolean state=reviewService.commentUpdate(coid,user.getUid(),commentUpdateRequestDto);
        if(state){
            map.put("result","사장님 댓글이 수정되었습니다~");
        }else{
            map.put("result","수정중 오류가 발생했습니다.");
        }
        return map;
    }

    // 사장님 댓글 삭제
    @ApiOperation("사장님 댓글 삭제 하기")
    @DeleteMapping("/latte/comment/delete/{coid}")  // rvuid
    public Map commentDelete(HttpServletRequest httpServletRequest, @PathVariable Long coid) {
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);
        Map<String,String> map=new HashMap<>();
        boolean state=reviewService.commentDelete(coid,user.getUid());
        if(state){
            map.put("result","사장님 댓글이 삭제되었습니다~");
        }else{
            map.put("result","삭제중 오류가 발생했습니다.");
        }
        return map;
    }


}
