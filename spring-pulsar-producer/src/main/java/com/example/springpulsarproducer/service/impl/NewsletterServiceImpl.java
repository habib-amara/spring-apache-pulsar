package com.example.springpulsarproducer.service.impl;


import com.example.springpulsarproducer.dto.NewsletterDTO;
import com.example.springpulsarproducer.dto.Notification;
import com.example.springpulsarproducer.entities.Newsletter;
import com.example.springpulsarproducer.service.NewsletterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.shade.org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

/**
 * Service Implementation for managing {@link Newsletter}.
 *
 * @author aek
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NewsletterServiceImpl implements NewsletterService {

    private final Producer<Notification> producer;

    /**
     * {@inheritDoc}
     */
    @Override
    public Newsletter sendNewsletter(NewsletterDTO newsletterDTO) {

        Newsletter newsletter = Newsletter.builder()
                .channel("EMAIL").content(newsletterDTO.getContent())
                .title(newsletterDTO.getTitle()).createdAt(LocalDateTime.now())
                .publishedAt(LocalDateTime.now()).type(newsletterDTO.getType())
                .build();

        if (ObjectUtils.isNotEmpty(newsletter)) {
            sendToSubscriber(newsletter);
        }

        return newsletter;
    }

    private void sendToSubscriber(Newsletter newsletterSaved) {


        Notification notification = Notification.builder()
                .channel(newsletterSaved.getChannel()).content(newsletterSaved.getContent())
                .recipientEmail("habib@mail.com").recipientName("Habib")
                .subject(newsletterSaved.getTitle())
                .build();
        sendMsgAsync(notification);

    }

    /**
     * Pulsar publish messages asynchronously using the Java client
     */
    private void sendMsgAsync(Notification notification) {
        producer.sendAsync(notification).thenAccept(msgId -> log.info("Notification message with ID {} successfully sent", msgId));
    }
}
