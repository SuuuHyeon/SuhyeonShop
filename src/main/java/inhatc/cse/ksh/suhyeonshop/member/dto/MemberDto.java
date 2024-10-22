package inhatc.cse.ksh.suhyeonshop.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 사항입니다.")
    @Length(min = 3, max = 16, message = "비밀번호는 3자 이상 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "주소는 필수 입력 사항입니다.")
    private String address;
}
