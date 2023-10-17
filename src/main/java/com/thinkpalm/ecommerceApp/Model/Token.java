package com.thinkpalm.ecommerceApp.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Integer tokenid;

        private String token;

        @Enumerated(EnumType.STRING)
        private TokenType tokenType;

        private boolean expired;

        private boolean revoked;

        @ManyToOne
        @JoinColumn(name = "customerId")
        private Customer customer;


}
