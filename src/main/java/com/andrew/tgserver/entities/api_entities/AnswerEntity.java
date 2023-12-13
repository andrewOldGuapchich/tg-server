package com.andrew.tgserver.entities.api_entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {
    private int answerCode;
    private List<PictureEntity> pictures;
}
