package com.careup.branch.branch.chat.dto.req;


import com.careup.branch.branch.chat.domain.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberReqDto {
    private MemberType memberType;
    private String email;
}
