package dev.showhub.show;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "show_event")
public class ShowEvent extends PanacheEntityBase {

    @Id
    public UUID id;

    public String name;
    public String city;
    public LocalDate date;

    @PrePersist
    void pre() {
        if (id == null) id = UUID.randomUUID();
    }
}
