package com.convertPdfA.convertPdfA.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;



import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spire.pdf.conversion.PdfStandardsConverter;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ConvertPdfaServices {
	
	
	
	public void convertToPdfA2B(InputStream inputStream, HttpServletRequest request) throws IOException {
	    // Verificando se o diretório existe, caso contrário, criá-lo
	    File directory = new File(request.getServletContext().getRealPath("/resources/pdf/"));
	    if (!directory.exists()) {
	        directory.mkdirs(); // Cria os diretórios pais e o diretório especificado
	    }
	    
	    // Criando o arquivo de saída
	    File outputFile = new File(directory, "teste.pdf");
	    
	    // Convertendo para PDF
	    PdfStandardsConverter converter = new PdfStandardsConverter(inputStream);
	    try (FileOutputStream stream = new FileOutputStream(outputFile)) {
	        converter.toPdfA2B(stream);
	    }
	    
	}
	
	public ResponseEntity<Resource> getFile(HttpServletRequest request) throws IOException{

		Path pathFile = Paths.get(request.getServletContext().getRealPath("/resources/pdf/teste.pdf"))
				.toAbsolutePath()
				.normalize();
		Resource resource = new UrlResource(pathFile.toUri());
		
		HttpHeaders header = new HttpHeaders();
		header.setContentDispositionFormData(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= \"arquivo.pdf\"");
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(header)
				.body(resource);
		
		
	}
	
	
    public byte[] getBytesFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytesArray = new byte[(int) file.length()];
        fis.read(bytesArray); //read file into bytes[]
        fis.close();
        return bytesArray;
    }

}
