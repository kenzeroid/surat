package com.pemerintah.surat.service;

import com.pemerintah.surat.model.ExportJasper;
import com.pemerintah.surat.model.SuratPerjalananModel;
import net.sf.jasperreports.engine.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SuratPerjalananService implements SuratPerjalanan{
    public ResponseEntity<?> getSuratPerjalanan(HttpServletResponse httpServletResponse, SuratPerjalananModel suratPerjalanan) throws JRException, IOException, ParseException {

        suratPerjalananData(suratPerjalanan);

//        File file = ResourceUtils.getFile("classpath:SuratPerjalananDinas.jrxml");
//        File logo = ResourceUtils.getFile("classpath:img.png");
//
        InputStream image = getClass().getClassLoader().getResourceAsStream("img.png");
        assert image != null;
        BufferedImage logo = ImageIO.read(image);

        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream("SuratPerjalananDinas.jrxml"));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Nomor", suratPerjalanan.getNomor());
        parameters.put("Wenang", suratPerjalanan.getWenang());
        parameters.put("Nama", suratPerjalanan.getNama());
        parameters.put("Golongan", suratPerjalanan.getGolongan());
        parameters.put("Jabatan", suratPerjalanan.getJabatan());
        parameters.put("Tingkat", suratPerjalanan.getTingkat());
        parameters.put("Tujuan", suratPerjalanan.getTujuan());
        parameters.put("Kendaraan", suratPerjalanan.getKendaraan());
        parameters.put("TempatBerangkat", suratPerjalanan.getTempatBerangkat());
        parameters.put("TempatTujuan", suratPerjalanan.getTempatTujuan());
        parameters.put("LamaPerjalanan", suratPerjalanan.getLamaPerjalanan());
        parameters.put("TanggalBerangkat", suratPerjalanan.getTanggalBerangkat());
        parameters.put("TanggalKembali", suratPerjalanan.getTanggalKembali());
        parameters.put("Instansi", suratPerjalanan.getInstansi());
        parameters.put("MataAnggaran", suratPerjalanan.getMataAnggaran());
        parameters.put("Ditetapkan", suratPerjalanan.getDitetapkan());
        parameters.put("TanggalSurat", suratPerjalanan.getTanggalSurat());
        parameters.put("Maksud", suratPerjalanan.getMaksud());
        parameters.put("Logo", logo);
        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        //Export report
//        JRDocxExporter exporter = new JRDocxExporter();
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(httpServletResponse.getOutputStream()));
//        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=jasperfile.docx");
//        httpServletResponse.setContentType("application/octet-stream");
//        exporter.exportReport();
        JasperExportManager.exportReportToPdfStream(jasperPrint,httpServletResponse.getOutputStream());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> exportJasperReport(HttpServletResponse response, ExportJasper exportJasper) throws JRException, IOException {
        //Get file and compile it
        File file = ResourceUtils.getFile("classpath:Blank_A4.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("kopsurat", exportJasper.getKopsurat());
        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        //Export report
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private SuratPerjalananModel suratPerjalananData(SuratPerjalananModel suratPerjalanan) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String format = "dd MMMM yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        suratPerjalanan.setTanggalSurat(suratPerjalanan.getTanggalSurat()==null?formatter.format(date):formatter.format(sdf.parse(suratPerjalanan.getTanggalSurat())));
        suratPerjalanan.setTanggalBerangkat(suratPerjalanan.getTanggalBerangkat()==null?formatter.format(date):formatter.format(sdf.parse(suratPerjalanan.getTanggalBerangkat())));
        suratPerjalanan.setTanggalKembali(suratPerjalanan.getTanggalKembali()==null?formatter.format(date):formatter.format(sdf.parse(suratPerjalanan.getTanggalKembali())));
        return suratPerjalanan;
    }
}
