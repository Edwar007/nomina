package com.innovatech.solution.nomina.service.impl;

import com.innovatech.solution.nomina.criteria.PagoNominaCriteria;
import com.innovatech.solution.nomina.dta.PagoNomina;
import com.innovatech.solution.nomina.dta.PagoNomina_;
import com.innovatech.solution.nomina.dto.BusquedaPagosDTO;
import com.innovatech.solution.nomina.repository.PagoNominaRepository;
import com.innovatech.solution.nomina.service.ConsultasPagoService;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultasPagoServiceImpl extends QueryService<PagoNomina> implements ConsultasPagoService {


    @Autowired
    PagoNominaRepository pagoNominaRepository;
    @Override
    public List<PagoNomina> busquedaPagos(BusquedaPagosDTO busquedaDTO){
        PagoNominaCriteria pagoCriteria = (PagoNominaCriteria) createCriteria(busquedaDTO);
        List<PagoNomina> listPagos = findByCriteria(pagoCriteria);
        return listPagos;
    }
    private PagoNominaCriteria createCriteria(BusquedaPagosDTO dto){
        PagoNominaCriteria pagoCriteria = new PagoNominaCriteria();

        if(dto!=null){
            if(!StringUtils.isBlank(dto.getIdentificacion())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getIdentificacion());
                pagoCriteria.setIdPersona(filter);
            }

            if(dto.getFecha()!=null){
                LocalDateFilter filter = new LocalDateFilter();
                filter.setEquals(dto.getFecha());
                pagoCriteria.setFecha(filter);
            }

            if(dto.getTotDevDesde()!=null || dto.getTotDevHasta()!=null){
                DoubleFilter filter = new DoubleFilter();
                if(dto.getTotDevDesde()!=null){
                    filter.setGreaterThanOrEqual(dto.getTotDevDesde());
                    pagoCriteria.setTotDev(filter);
                }

                if(dto.getTotDevHasta()!=null){
                    filter.setLessThanOrEqual(dto.getTotDevHasta());
                    pagoCriteria.setTotDev(filter);
                }
            }

            if(dto.getTotDesDesde()!=null || dto.getTotDesHasta()!=null){
                DoubleFilter filter = new DoubleFilter();
                if(dto.getTotDesDesde()!=null){
                    filter.setGreaterThanOrEqual(dto.getTotDesDesde());
                    pagoCriteria.setTotDes(filter);
                }

                if(dto.getTotDesHasta()!=null){
                    filter.setLessThanOrEqual(dto.getTotDesHasta());
                    pagoCriteria.setTotDes(filter);
                }
            }

            if(dto.getPagFinDesde()!=null || dto.getPagFinHasta()!=null){
                DoubleFilter filter = new DoubleFilter();
                if(dto.getPagFinDesde()!=null){
                    filter.setGreaterThanOrEqual(dto.getPagFinDesde());
                    pagoCriteria.setPagoFinal(filter);
                }

                if(dto.getPagFinHasta()!=null){
                    filter.setLessThanOrEqual(dto.getPagFinHasta());
                    pagoCriteria.setPagoFinal(filter);
                }
            }
        }
        return pagoCriteria;
    }
    private List<PagoNomina> findByCriteria(PagoNominaCriteria pagoCriteria){

        final Specification<PagoNomina> specification = createSpecification(pagoCriteria);
        List<PagoNomina> pagosNomina = pagoNominaRepository.findAll(specification);
        return pagosNomina;
    }
    private Specification<PagoNomina> createSpecification(PagoNominaCriteria criteria){
        Specification<PagoNomina> specification = Specification.where(null);

        if(criteria != null){
            if(criteria.getIdPersona()!=null){
                specification =
                        specification.and(buildStringSpecification(criteria.getIdPersona(), PagoNomina_.idPersona));
            }
            if(criteria.getFecha()!=null){
                specification =
                        specification.and(buildRangeSpecification(criteria.getFecha(), PagoNomina_.fecha));
            }
            if(criteria.getTotDev()!=null){
                specification =
                        specification.and(buildRangeSpecification(criteria.getTotDev(), PagoNomina_.totDev));
            }
            if(criteria.getTotDes()!=null){
                specification =
                        specification.and(buildRangeSpecification(criteria.getTotDes(), PagoNomina_.totDes));
            }
            if(criteria.getPagoFinal()!=null){
                specification =
                        specification.and(buildRangeSpecification(criteria.getPagoFinal(), PagoNomina_.pagoFinal));
            }
        }
        return specification;
    }
}
