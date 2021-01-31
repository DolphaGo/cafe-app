package com.cafe.dolphago.web;

import com.cafe.dolphago.domain.menu.Menu;
import com.cafe.dolphago.service.MenuService;
import com.cafe.dolphago.web.dto.menu.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping("/latte/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // 메뉴저장
    @ApiOperation("[사장님 페이지]:카페에서 메뉴 추가시 저장하는 기능")
    @PostMapping("/{ccid}")
    public Map save(@PathVariable Long ccid, @RequestBody MenuSaveRequestDto menuSaveRequestDto) {
        // 메뉴
        Map<String,String> map=new HashMap<>();
        Long mmid=menuService.save(menuSaveRequestDto,ccid); //메뉴 save -> mmid가 나와.
        Menu menu=menuService.findById(mmid);

        // 사이즈
        List<MenuSizeRequestDto> menuSizeRequestDtoList=menuSaveRequestDto.getMenuSizeRequestDtos();
        for(MenuSizeRequestDto ms:menuSizeRequestDtoList){
            menuService.MenuSizesave(ms.toEntity(menu));
    }

        map.put("result","메뉴가 저장되었습니다~");
        return map;
    }

    // 옵션 저장
    @ApiOperation("[사장님 페이지]:카페에서 옵션 추가시 저장하는 기능")
    @PostMapping("/option/{mmid}")
    public Map optionAdd(@PathVariable Long mmid, @RequestBody List<MenuOptionRequestDto> menuOptionRequestDto) {

        // 메뉴
        Map<String,String> map=new HashMap<>();
        Menu menu=menuService.findById(mmid);

        // 옵션
        for(MenuOptionRequestDto mo:menuOptionRequestDto){
            menuService.Optionsave(mo,menu);
        }

        map.put("result","옵션이 저장되었습니다~");
        return map;
    }

    
    // 한 카페가 보유하고있는 모든 메뉴 출력
    @ApiOperation("[주문페이지/카페소개도?]:한 카페가 보유하고있는 모든 메뉴 출력")
    @GetMapping("/{ccid}")
    public List<MenuResponseDto> selectAll(@PathVariable Long ccid){
        return menuService.selectAll(ccid);
    }

    // 메뉴 선택시 해당 메뉴 관련 정보 제공(해당 카페, 가격, 이름 사진 등)
    @ApiOperation("[언제사용..?]: 메뉴 선택시 해당 메뉴 관련 정보 제공(해당 카페, 가격, 이름 사진 등)")
    @GetMapping("/one/{mmid}")
    public MenuResponseDto selectOne(@PathVariable Long mmid){
        return menuService.selectOne(mmid);
    }

    // jwt 필요
    // menu update(한번에 옵션, 사이즈 메뉴정보 다 할거임)
    @ApiOperation("[사장님페이지]: 메뉴를 업데이트 하는 기능")
    @PutMapping("/update/{mmid}")
    public Map update(@RequestBody MenuUpdateRequestDto menuUpdateRequestDto, @PathVariable Long mmid){
        Map<String,String> map=new HashMap<>();
        menuService.update(mmid,menuUpdateRequestDto); //메뉴이름 , 사진 업데이트
        //사이즈와 가격 업데이트
        List<MenuSizeUpdateDto> list=menuUpdateRequestDto.getMenuSizeUpdateDtoList();
        for(MenuSizeUpdateDto menuSizeUpdateDto:list){
            menuService.updateSize(mmid,menuSizeUpdateDto);
        }
        //옵션들 업데이트
        for(MenuOptionUpdateRequestDto menuOptionUpdateRequestDto: menuUpdateRequestDto.getMenuOptionUpdateRequestDtoList()){
            menuService.updateOption(mmid,menuOptionUpdateRequestDto);
        }
        map.put("result" , "메뉴 수정이 완료되었습니다~");
        return map;
    }

    // menu delete
    @ApiOperation("[사장님페이지]:선택된 메뉴를 삭제하는 기능")
    @DeleteMapping("/delete/{mmid}")
    public void delete(@PathVariable Long mmid){
        menuService.delete(mmid);
    }


    @ApiOperation("메인메뉴 설정/취소(버튼으로 토글)")
    @PutMapping("/toggleMain/{mmid}")
    public void toggleMain(@PathVariable Long mmid){
        menuService.toggleMain(mmid);
    }

}