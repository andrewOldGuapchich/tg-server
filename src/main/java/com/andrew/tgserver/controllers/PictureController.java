package com.andrew.tgserver.controllers;

import com.andrew.tgserver.services.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PictureController {
    private final QueryService queryService;

    @GetMapping("/picture")
    public ResponseEntity<?> getPictureUrl(@RequestParam(name = "query", required = false) String query,
                                           @RequestParam(name = "count", required = false) int count){
        return queryService.getPath(query, count);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategory(){
        return queryService.getCategory();
    }

}
