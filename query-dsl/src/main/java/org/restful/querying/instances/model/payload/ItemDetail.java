/**
 * Copyright 2020 the project restful-querying-instances authors
 * and the original author or authors annotated by {@author}
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.restful.querying.instances.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.restful.querying.instances.controller.ItemController;
import org.restful.querying.instances.model.entity.Item;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @project restful-querying-instances
 * @created 24.05.2020 10:52
 * <p>
 * @author Alexander A. Kropotin
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(
        of = {
                "id"
        },
        doNotUseGetters = true
)
@Data
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
@Relation(value = "item")
public class ItemDetail extends ResourceSupport {

    @JsonProperty("uid")
    Long uid;

    @JsonProperty("name")
    String name;

    @JsonProperty("length")
    Integer length;

    public static ItemDetail getInstance(Item item) {
        if (item == null) return null;

        ItemDetail itemDetail = ItemDetail
                .builder()
                .uid(item.getUid())
                .name(item.getName())
                .length(item.getLength())
                .build();

        itemDetail.add(selfLinkOf(item.getUid()));

        return itemDetail;
    }

    static Link selfLinkOf(Long uid) {
        return linkTo(methodOn(ItemController.class).find(uid)).withSelfRel();
    }
}
