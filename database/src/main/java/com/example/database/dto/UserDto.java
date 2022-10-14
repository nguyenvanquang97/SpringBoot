package com.example.database.dto;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private  Integer id;
    private String name;
    private String email;

}
//c1:Query User=>convert to dto
//convert tu viet
//su dung dependence (mapstruct,modeMapper...)
// c2:su dung JPA query language
//c3:su dung projection
//c4:su dung native query
