package com.cafe.dolphago.web;

import com.cafe.dolphago.domain.cafe.Cafe;
import com.cafe.dolphago.service.CafeService;
import com.cafe.dolphago.service.jwt.JwtService;
import com.cafe.dolphago.service.jwt.UnauthorizedException;
import com.cafe.dolphago.web.dto.cafe.*;
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
@RequestMapping("/latte/cafe")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;
    private final JwtService jwtService;

    @ApiOperation("[사장님 회원가입페이지]: 회원가입 시 카페 내용 저장")
    @PostMapping("/create")
    public Map save(@RequestBody CafeSaveRequestDto cafeSaveRequestDto, HttpServletRequest httpServletRequest){
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);
        Map<String,Long> map=new HashMap<>();
        map.put("result",cafeService.save(user.getUid(), cafeSaveRequestDto));
        return map;
    }

    // 카페 실제로 열었는지에 대한 상태 변경
    @ApiOperation("[사장님 카페 관리페이지]: 카페 운영중/운영마감 변경")
    @PostMapping("/opeartion")
    public Map cafeOpeartion(HttpServletRequest httpServletRequest, @RequestBody CafeOpenRequestDto cafeOpenRequestDto) {
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);


        Long ccid=cafeOpenRequestDto.getCcid();
        int coperation=cafeOpenRequestDto.getCoperation();
        Cafe curCafe=cafeService.findByCcId(ccid);

        if(user.getUid().equals(curCafe.getUid())){
            curCafe.setCoperation(coperation);
            Map<String,Integer> map=new HashMap<>();
            map.put("변경 후 상태",cafeService.findByCcId(ccid).getCoperation());
            return map;
        }else throw new UnauthorizedException(); // 예외
}

    // 카페 리스트 보여주기
    @ApiOperation("[손님 카페소개페이지]:카페 리스트를 손님들에게 보여줌-> 실제로 영업중인 것들을 우선적으로 보여줌")
    @GetMapping("/all")
    public List<CafeListResponseDto> selectAll() {
        return cafeService.findByOperation();
    }


    // 카페 정보 수정
    @ApiOperation("[사장님 카페 정보 관리페이지]:특정 카페 정보 수정")
    @PutMapping("/update/{ccid}")
    public void cafeUpdate(HttpServletRequest httpServletRequest,@PathVariable Long ccid, @RequestBody CafeUpdateRequestDto cafeUpdateRequestDto) {
        String jwt = httpServletRequest.getHeader("Authorization");

//        System.out.println("현재 토큰 : "+jwt);
//        System.out.println("유효성 : "+ jwtService.isUsable(jwt));
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외

        UserJwtResponsetDto user=jwtService.getUser(jwt);

       if(cafeUpdateRequestDto.getUid().equals(user.getUid())){ //수정할 권한이 있으면
           cafeService.cafeUpdate(ccid,cafeUpdateRequestDto);
       }else throw new UnauthorizedException(); // 예외
    }

    // ccid로 카페 하나 찾기 -> cafe + menu
    @ApiOperation("[손님 카페Detail페이지]:ccid를 기준으로 하나의 카페 정보 찾기")
    @GetMapping("/{ccid}")
    public CafeDetailForGUEST selectOne(@PathVariable Long ccid) {
        return new CafeDetailForGUEST(cafeService.findByCcId(ccid));
    }

}