package com.innovatech.solution.nomina.service.impl;

import com.innovatech.solution.nomina.criteria.PersonaCriteria;
import com.innovatech.solution.nomina.dta.Area_;
import com.innovatech.solution.nomina.dta.Cargo_;
import com.innovatech.solution.nomina.dta.Persona;
import com.innovatech.solution.nomina.dta.Persona_;
import com.innovatech.solution.nomina.dto.BusquedaPersonasDTO;
import com.innovatech.solution.nomina.repository.PersonaRepository;
import com.innovatech.solution.nomina.service.ConsultasPersonaService;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultasPersonaServiceImpl extends QueryService<Persona> implements ConsultasPersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Persona> busquedaPersonas(BusquedaPersonasDTO busquedaDTO){
        PersonaCriteria personaCriteria = createCriteria(busquedaDTO);
        List<Persona> listPersonas = findByCriteria(personaCriteria);
        return listPersonas;
    }

    private PersonaCriteria createCriteria(BusquedaPersonasDTO dto){
        PersonaCriteria personaCriteria = new PersonaCriteria();
        if(dto!= null){
            if(!StringUtils.isBlank(dto.getIdentificacion())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getIdentificacion());
                personaCriteria.setIdentificacion(filter);
            }

            if(!StringUtils.isBlank(dto.getApellidos())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getApellidos());
                personaCriteria.setApellidos(filter);
            }

            if(!StringUtils.isBlank(dto.getEstado())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getEstado());
                personaCriteria.setEstado(filter);
            }

            if(dto.getSalarioDesde()!=null || dto.getSalarioHasta()!=null){
                DoubleFilter filter = new DoubleFilter();
                if(dto.getSalarioDesde()!=null){
                    filter.setGreaterThanOrEqual(dto.getSalarioDesde());
                    personaCriteria.setSalario(filter);
                }

                if(dto.getSalarioHasta()!=null){
                    filter.setLessThanOrEqual(dto.getSalarioHasta());
                    personaCriteria.setSalario(filter);
                }
            }
            if(!StringUtils.isBlank(dto.getArea())){
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getArea());
                personaCriteria.setArea(filter);
            }
            if(!StringUtils.isBlank(dto.getCargo())){
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getCargo());
                personaCriteria.setCargo(filter);
            }
        }
        return personaCriteria;
    }

    public List<Persona> findByCriteria(PersonaCriteria personaCriteria){
        final Specification<Persona> specification = createSpecification(personaCriteria);
        List<Persona> personas = personaRepository.findAll(specification);
        return personas;
    }

    private  Specification<Persona> createSpecification(PersonaCriteria criteria){
        Specification<Persona> specification = Specification.where(null);

        if(criteria != null){
            if(criteria.getIdentificacion()!=null){
                specification =
                        specification.and(buildStringSpecification(criteria.getIdentificacion(), Persona_.identificacion));
            }
            if(criteria.getApellidos()!=null){
                specification =
                        specification.and(buildStringSpecification(criteria.getApellidos(), Persona_.apellidos));
            }
            if(criteria.getEstado()!=null){
                specification =
                        specification.and(buildStringSpecification(criteria.getEstado(), Persona_.estado));
            }
            if(criteria.getSalario()!=null){
                specification =
                        specification.and(buildRangeSpecification(criteria.getSalario(), Persona_.salario));
            }
            if(criteria.getArea()!=null){
                specification=
                        specification.and(buildReferringEntitySpecification(criteria.getArea(), Persona_.area, Area_.nombre));

            }
            if(criteria.getCargo()!=null){
                specification=
                        specification.and(buildReferringEntitySpecification(criteria.getCargo(), Persona_.cargo, Cargo_.nombre));

            }
        }
        return specification;
    }
}
