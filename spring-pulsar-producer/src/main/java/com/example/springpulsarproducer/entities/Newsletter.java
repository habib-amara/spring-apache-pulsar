package com.example.springpulsarproducer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * JPA entity class for "Newsletter"
 *
 * @author aek
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Newsletter implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id ;


    private String title ;


    private String content ;


    private String channel ;


    private String type ;


    private LocalDateTime publishedAt ;

    private LocalDateTime createdAt ;

}
