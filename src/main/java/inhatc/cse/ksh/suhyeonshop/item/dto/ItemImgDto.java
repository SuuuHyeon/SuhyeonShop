package inhatc.cse.ksh.suhyeonshop.item.dto;


import inhatc.cse.ksh.suhyeonshop.item.entity.Item;
import inhatc.cse.ksh.suhyeonshop.item.entity.ItemImg;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemImgDto {

    private Long id;

    private String imgName;         // 이미지 이름

    private String oriImgName;      // 원본 이미지 이름

    private String imgUrl;          // 이미지 경로

    private String repImgYn;       // 대표 이미지 여

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);  // itemImg 받아오면 dto로 변환
    }
}
