package com.green.firstproject.vo.order;

import java.util.Set;

import lombok.Data;
@Data
public class OrderFormVO {
     private Long pay;
     private Set<Long> cartSeq;
     private String message;
}
