package org.restful.querying.instances.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(
        of = {
                "name"
        },
        doNotUseGetters = true
)
@Data
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
@Entity(name = "Item")
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_pkey"
    )
    @SequenceGenerator(
            name = "item_pkey",
            sequenceName = "item_uid_seq",
            allocationSize = 1
    )
    @Column(
            name = "uid",
            insertable = false,
            nullable = false
    )
    Long uid;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "length")
    Integer length;
}