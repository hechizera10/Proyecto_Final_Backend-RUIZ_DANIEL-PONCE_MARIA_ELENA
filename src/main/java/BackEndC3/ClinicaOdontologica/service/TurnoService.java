package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.entity.Turno;
import BackEndC3.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public List<Turno> buscarTodos(){
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarPorId(Long id){
        return turnoRepository.findById(id);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }





}
