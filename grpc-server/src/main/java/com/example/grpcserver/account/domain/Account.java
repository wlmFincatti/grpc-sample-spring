package com.example.grpcserver.account.domain;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Account {

    private Integer idAccount;
    private String brand;
    private Integer agency;
    private Integer number;
    private Integer digit;

}
