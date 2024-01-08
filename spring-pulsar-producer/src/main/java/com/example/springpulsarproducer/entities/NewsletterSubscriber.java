package com.example.springpulsarproducer.entities;

import lombok.*;

import java.io.Serializable;

/**
 * JPA entity class for "NewsletterSubscriber"
 *
 * @author aek
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NewsletterSubscriber implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private String name ;

}
