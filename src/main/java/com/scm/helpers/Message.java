package com.scm.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    
    private String content;
    // since @Builder annotation warns about the default value, 
    // we can use @Builder.Default to set the default value
    @Builder.Default
    private MessageType type = MessageType.blue;

}
