package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "packages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String trackingNumber;
    private String senderName;
    private String recieverName;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime lastUpdated;

    public enum DeliveryStatus {
        PENDING,
        IN_TRANSIT,
        DELIVERED;
    }

    @Builder
    public Package(String trackingNumber, String senderName, String recieverName, DeliveryStatus deliveryStatus) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.recieverName = recieverName;
        this.deliveryStatus = deliveryStatus;
    }

    @PrePersist
    public void prePersist() {
        this.lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }
}
