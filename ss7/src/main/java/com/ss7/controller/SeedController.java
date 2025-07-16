package com.ss7.controller;

import com.ss7.model.dto.DataResponse;
import com.ss7.model.entity.Seed;
import com.ss7.service.seed.SeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seeds")
@RequiredArgsConstructor
public class SeedController{

    private final SeedService seedService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Seed>>> findAll(){
        return ResponseEntity.ok(new DataResponse<>(seedService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Seed>> save(@RequestBody Seed seed){
        return ResponseEntity.ok(new DataResponse<>(seedService.save(seed), HttpStatus.CREATED));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<Seed>> findById(@PathVariable Long id){
        return ResponseEntity.ok(new DataResponse<>(seedService.findById(id), HttpStatus.OK));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataResponse<Seed>> update(@RequestBody Seed seed){
        return ResponseEntity.ok(new DataResponse<>(seedService.update(seed), HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Void>> deleteById(@PathVariable Long id){
        seedService.deleteById(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }
}
