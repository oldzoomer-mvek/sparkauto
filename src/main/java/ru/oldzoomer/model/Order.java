package ru.oldzoomer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_works",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id"))
    private List<Work> works;

    public double getTotalHours() {
        return works.stream().mapToDouble(Work::getNormalHours).sum();
    }

    public double getTotalPrice() {
        return works.stream()
                .mapToDouble(w -> w.getNormalHours() * w.getPricePerHour())
                .sum();
    }
}
