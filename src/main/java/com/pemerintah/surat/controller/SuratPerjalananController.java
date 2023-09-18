package com.pemerintah.surat.controller;

import com.pemerintah.surat.model.*;
import com.pemerintah.surat.service.SuratPerjalanan;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/v1/suratPerjalanan")
public class SuratPerjalananController {
    private final SuratPerjalanan suratPerjalanan;

    @Autowired
    public SuratPerjalananController(SuratPerjalanan suratPerjalanan) {
        this.suratPerjalanan = suratPerjalanan;
    }

    @PostMapping(value = "cetak")
    public ResponseEntity<?> getSuratPerjalanan(HttpServletResponse httpServletResponse, @RequestBody SuratPerjalananModel suratPerjalananModel) {
        try {
            String pattern = "MM-dd-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateNow = simpleDateFormat.format(new Date());
            return ResponseEntity
                    .ok()
                    // Specify content type as PDF
                    .header("Content-Type", "application/pdf; charset=UTF-8")
                    // Tell browser to display PDF if it can
                    .header("Content-Disposition", "inline; filename=\"" + dateNow + ".pdf\"")
                    .body(suratPerjalanan.getSuratPerjalanan(httpServletResponse, suratPerjalananModel));
        } catch (ParseException | JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "exportJasperReport")
    public ResponseEntity<?> exportJasperReport(HttpServletResponse httpServletResponse, @RequestBody ExportJasper exportJasper) {
        try {
            return ResponseEntity.ok(suratPerjalanan.exportJasperReport(httpServletResponse, exportJasper));
        } catch (IOException | JRException e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping(value = "test")
//    public ResponseEntity<?> test(){
//        return ResponseEntity.ok(ResponseTest.builder()
//                        .errorSchema(ErrorSchema.builder()
//                                .errorStatus("201")
//                                .errorMessage(ErrorSchema.ErrorMessage.builder()
//                                        .errorInd("Tidak tersedia")
//                                        .errorEng("Unavailable")
//                                        .build())
//                                .build())
//                        .status("Berhasil")
//                .build());
//    }
}
