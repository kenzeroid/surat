package com.pemerintah.surat.controller;

import com.pemerintah.surat.model.ExportJasper;
import com.pemerintah.surat.model.SuratPerjalananModel;
import com.pemerintah.surat.service.SuratPerjalanan;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/v1/suratPerjalanan")
public class SuratPerjalananController {
    private final SuratPerjalanan suratPerjalanan;

    @Autowired
    public SuratPerjalananController(SuratPerjalanan suratPerjalanan) {
        this.suratPerjalanan = suratPerjalanan;
    }

    @PostMapping(value = "cetak")
    public ResponseEntity<?> getSuratPerjalanan(HttpServletResponse httpServletResponse, @RequestBody SuratPerjalananModel suratPerjalananModel) throws JRException, IOException {
        try {
            return ResponseEntity.ok(suratPerjalanan.getSuratPerjalanan(httpServletResponse, suratPerjalananModel));
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "exportJasperReport")
    public ResponseEntity<?> exportJasperReport(HttpServletResponse httpServletResponse, @RequestBody ExportJasper exportJasper) throws JRException, IOException {
        try {
            return ResponseEntity.ok(suratPerjalanan.exportJasperReport(httpServletResponse, exportJasper));
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
