package com.ss7.controller;

import com.ss7.model.dto.DataResponse;
import com.ss7.model.entity.Harvest;
import com.ss7.service.harvest.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
public class HarvestController{

    private final HarvestService harvestService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Harvest>>> findAll(){
        return ResponseEntity.ok(new DataResponse<>(harvestService.getAllHarvests(), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<Harvest>> findById(@PathVariable Long id){
        return ResponseEntity.ok(new DataResponse<>(harvestService.getHarvestById(id), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Harvest>> save(@RequestBody Harvest harvest){
        return ResponseEntity.ok(new DataResponse<>(harvestService.addHarvest(harvest), HttpStatus.CREATED));
    }
}
