package com.develop.webflux.context;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FluxRequest {
    private String priorityCard;
    private String messageCard;
    private String creatorCard;
}
