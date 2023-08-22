package com.pemerintah.surat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuratPerjalananModel {
    private String Nomor;
    private String Wenang;
    private String Nama;
    private String Golongan;
    private String Jabatan;
    private String Tingkat;
    private String Tujuan;
    private String Kendaraan;
    private String TempatBerangkat;
    private String TempatTujuan;
    private String LamaPerjalanan;
    private String TanggalBerangkat;
    private String TanggalKembali;
    private String Instansi;
    private String MataAnggaran;
    private String Penetapan;
    private String TanggalSurat;
    private String Ditetapkan;
    private String Maksud;
}
