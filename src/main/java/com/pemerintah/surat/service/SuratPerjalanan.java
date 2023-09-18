package com.pemerintah.surat.service;

import com.pemerintah.surat.model.ExportJasper;
import com.pemerintah.surat.model.SuratPerjalananModel;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public interface SuratPerjalanan {
    byte[] getSuratPerjalanan(HttpServletResponse httpServletResponse, SuratPerjalananModel suratPerjalananModel) throws JRException, IOException, ParseException;
    ResponseEntity<?> exportJasperReport(HttpServletResponse httpServletResponse, ExportJasper exportJasper) throws JRException, IOException;
}
