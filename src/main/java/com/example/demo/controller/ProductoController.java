package com.example.demo.controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Producto;
import com.example.demo.service.IProductoService;

@RestController
@RequestMapping("/api/excel")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;

	@PostMapping("/import")
	public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile file){

//	   List<Producto> prodList = new ArrayList<Producto>();
		try {
	    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);

	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	    	Producto tempProd = new Producto();

	        XSSFRow row = worksheet.getRow(i);

//	        tempProd.setId((int) row.getCell(0).getNumericCellValue());
	        tempProd.setDescripcion(row.getCell(0).getStringCellValue());
	        tempProd.setNombre(row.getCell(1).getStringCellValue());	        
	        tempProd.setPrecio((int) row.getCell(2).getNumericCellValue());
//	        prodList.add(tempProd);
	        System.out.println(tempProd);
	        productoService.register(tempProd);
	    }
	    
//	    Reader reader = Files.newBufferedReader(file);
//	    CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
