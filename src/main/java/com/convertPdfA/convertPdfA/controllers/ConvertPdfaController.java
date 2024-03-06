package com.convertPdfA.convertPdfA.controllers;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.convertPdfA.convertPdfA.services.ConvertPdfaServices;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class ConvertPdfaController {
    
    @Autowired
    private ConvertPdfaServices services;
    
    @PostMapping(value = "/convert-to-pdfa")
    public ResponseEntity<HttpStatus> convertToPdfA(@RequestPart("file") MultipartFile pdfFile, HttpServletRequest request) throws IOException {
    	System.out.println("dp");
        services.convertToPdfA2B(pdfFile.getInputStream(), request);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }
    

    @GetMapping(value = "/download-pdf")
    public ResponseEntity<Resource> downloadPdf(HttpServletRequest request) throws IOException {
       System.out.println("pdf");
        return services.getFile(request);
    }

    



}
