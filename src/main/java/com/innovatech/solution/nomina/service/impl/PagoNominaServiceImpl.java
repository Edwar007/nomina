package com.innovatech.solution.nomina.service.impl;

import com.innovatech.solution.nomina.criteria.PagoNominaCriteria;
import com.innovatech.solution.nomina.dta.PagoNomina_;
import com.innovatech.solution.nomina.dto.BusquedaPagosDTO;
import com.innovatech.solution.nomina.dto.JasperDTO;
import com.innovatech.solution.nomina.dto.PagoNominaDTO;
import com.innovatech.solution.nomina.dta.PagoNomina;
import com.innovatech.solution.nomina.repository.PagoNominaRepository;
import com.innovatech.solution.nomina.service.PagoNominaService;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class PagoNominaServiceImpl implements PagoNominaService  {
    @Autowired
    PagoNominaRepository pagoNominaRepository;

    @Override
    public List<PagoNominaDTO> listarPagos(){
        /*
        List<PagoNominaDTO> lPagoNominaDTOS = new ArrayList<>();
        List<PagoNomina> listPagos = pagoNominaRepository.findAll();
        for (PagoNomina pago : listPagos) {
            lPagoNominaDTOS.add(PagoNominaDTO.builder()
                    .id(pago.getId())
                    .idPersona(pago.getIdPersona())
                    .fecha(pago.getFecha())
                    .subsidios(pago.getSubsidios())
                    .viaticos(pago.getViaticos())
                    .comisiones(pago.getComisiones())
                    .prima(pago.getPrima())
                    .gasRepre(pago.getGasRepre())
                    .salud(pago.getSalud())
                    .pension(pago.getPension())
                    .fondos(pago.getFondos())
                    .pagoFinal(pago.getPagoFinal())
                    .build());
        }

         */
        return null;
    }
    @Override
    public PagoNominaDTO calcularPago(PagoNominaDTO pago) {
        //Pago sobresueldos
        Double salMin = 1160000.0;
        Double valHorOrd = pago.getSalario() / 240;
        Double sobSueldo = pago.getSalario()+pago.getGasRepre() + pago.getViaticos() + pago.getComisiones() + (pago.getHorExtDiu()*valHorOrd*1.25) + (pago.getHorExtNoc()*valHorOrd*1.75) + (pago.getHorasExtDiuDomFes()*valHorOrd*2)+ (pago.getHorasExtNocDomFes()*valHorOrd * 2.50);

        //Pago devengados
        pago.setSalud(Math.round(sobSueldo * 0.04)+0.0);
        pago.setPension(Math.round(sobSueldo * 0.04)+0.0);
        //Calculo para fondo solidario
        double[] multiples = {4, 16, 17, 18, 19, 20};
        double[] percentages = {0.01, 0.012, 0.014, 0.016, 0.018, 0.02};
        for (int i = 0; i < multiples.length; i++) {
            if (sobSueldo >= salMin * multiples[i] && (i == multiples.length - 1 || sobSueldo < salMin * multiples[i + 1])) {
                pago.setFonSol(Math.round(sobSueldo * percentages[i])+0.0);
                break;
            }
        }
        if (pago.getFonSol() == null) {
            pago.setFonSol(0.0);
        }

        //Calculo TOTALES
        Double totDev = sobSueldo+pago.getPrima()+pago.getSubTrans()-pago.getSalario();
        Double totDes = pago.getSalud()+pago.getPension()+pago.getFonSol();
        pago.setTotDev(Math.round(totDev)+0.0);
        pago.setTotDes(Math.round(totDes)+0.0);
        pago.setPagoFinal((Math.round(pago.getSalario()+totDev - totDes)+0.0));
        return pago;
    }
    @Override
    public PagoNominaDTO crearPago(PagoNominaDTO pago) {
        PagoNomina crearPago = PagoNomina.builder()
                .id(pago.getId())
                .idPersona(pago.getIdPersona())
                .fecha(pago.getFecha())
                .prima(pago.getPrima())
                .subTrans(pago.getSubTrans())
                .gasRepre(pago.getGasRepre())
                .viaticos(pago.getViaticos())
                .comisiones(pago.getComisiones())
                .horExtDiu(pago.getHorExtDiu())
                .horExtNoc(pago.getHorExtNoc())
                .horasExtDiuDomFes(pago.getHorasExtDiuDomFes())
                .horasExtNocDomFes(pago.getHorasExtNocDomFes())
                .salud(pago.getSalud())
                .pension(pago.getPension())
                .fonSol(pago.getFonSol())
                .totDev(pago.getTotDev())
                .totDes(pago.getTotDes())
                .pagoFinal(pago.getPagoFinal())
                .build();
        System.out.println(crearPago);
        try {
            pagoNominaRepository.save(crearPago);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return pago;
    }
    @Override
    public ResponseEntity<ByteArrayResource> crearPdf(JasperDTO jasper){
        if (true) {
            try {
                final File file = ResourceUtils.getFile("classpath:Blank_A41.jasper");
                final File codBarras = ResourceUtils.getFile("classpath:static/img/qr.jpeg");
                final File logo = ResourceUtils.getFile("classpath:static/img/logo.jpeg");
                final JasperReport report = (JasperReport) JRLoader.loadObject(file);

                final HashMap<String, Object> parameters = new HashMap<>();


                parameters.put("nombre", jasper.getNombre());
                parameters.put("fecha", jasper.getFecha());
                parameters.put("cargo", jasper.getCargo());
                parameters.put("cedula", jasper.getCedula());
                parameters.put("cuenta", jasper.getCuenta());
                parameters.put("banco", jasper.getBanco());
                parameters.put("totalPagar", jasper.getTotalPagar());
                parameters.put("salario", jasper.getSalario());
                parameters.put("totDev", jasper.getTotDev());
                parameters.put("totDes", jasper.getTotDes());
                parameters.put("codBarras", new FileInputStream(codBarras));
                parameters.put("logo", new FileInputStream(logo));
                
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
                byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
                String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
                StringBuilder stringBuilder = new StringBuilder().append("InvoicePDF:");
                ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                        .filename(stringBuilder.append("pago")
                                .append("generateDate:")
                                .append(sdf)
                                .append(".pdf")
                                .toString())
                        .build();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentDisposition(contentDisposition);
                return ResponseEntity.ok().contentLength((long) reporte.length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .headers(headers).body(new ByteArrayResource(reporte));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ResponseEntity.noContent().build();
        }
        return null;
    }
    @Override
    public Double calPrima(LocalDate fec, Double salario){

        // Fechas para pagar prima
        LocalDate iniJun = LocalDate.of(fec.getYear(), Month.JUNE, 1);
        LocalDate finJun = LocalDate.of(fec.getYear(), Month.JUNE, 30);
        LocalDate iniDic = LocalDate.of(fec.getYear(), Month.DECEMBER, 1);
        LocalDate finDic = LocalDate.of(fec.getYear(), Month.DECEMBER, 20);

        if ((fec.isAfter(iniJun) && fec.isBefore(finJun)) || (fec.isAfter(iniDic) && fec.isBefore(finDic))) {
            return (salario/2);
        } else {
            return 0.0;
        }
    }

}
