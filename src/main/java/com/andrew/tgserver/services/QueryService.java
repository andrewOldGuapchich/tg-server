package com.andrew.tgserver.services;

import com.andrew.tgserver.api.UnsplashApi;
import com.andrew.tgserver.entities.Query;
import com.andrew.tgserver.entities.api_entities.AnswerEntity;
import com.andrew.tgserver.entities.api_entities.PictureEntity;
import com.andrew.tgserver.repositories.QueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final QueryRepository queryRepository;
    private final UnsplashApi unsplashApi;
    /**
     * возвращать ссылку на ресурс, чтобы на клиенте просто положить ее в путь
     */

    public ResponseEntity<?> getPath(String rusName, int count) {
        String queryRusName = null;
        queryRusName = upperFirstSymbol(URLDecoder.decode(rusName, StandardCharsets.UTF_8));

        String queryName = queryRepository.getQueryByRusName(queryRusName) != null ?
                queryRepository.getQueryByRusName(queryRusName).getEnName() : queryRusName;
        AnswerEntity answerEntity = unsplashApi.getPicture(queryName.toLowerCase(), count);
        if (answerEntity.getAnswerCode() == 200) {
            List<PictureEntity> pictures = answerEntity.getPictures();
            List<String> urls = new ArrayList<>();
            pictures.forEach(picture -> {
                urls.add(picture.getUrls().getRegular());
            });
            return ResponseEntity.ok(urls);
        } else if (answerEntity.getAnswerCode() == 403)
            return ResponseEntity.status(403).body(List.of("Rate Limit Exceeded"));
        else
            return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> getCategory(){
        try {
            List<String> category = queryRepository.findAll().stream()
                    .map(Query::getRusName)
                    .toList();
            return ResponseEntity.ok(category);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    private String upperFirstSymbol(String str){
        if(str == null || str.isEmpty())
            return str;
        String newStr = str.toLowerCase();
        return Character.toUpperCase(newStr.charAt(0)) + newStr.substring(1);
    }
}
