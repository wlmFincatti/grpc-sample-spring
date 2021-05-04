package com.example.grpcclient.domain;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Account {

    private Integer idAccount;
    private String brand;

}
