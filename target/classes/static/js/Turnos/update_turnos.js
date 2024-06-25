window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let turnoId = document.querySelector('#turno_id').value;

        const formData = {
            id: turnoId,
            paciente: document.querySelector('#paciente').value,
            odontologo: document.querySelector('#odontologo').value,
            fecha: document.querySelector('#fecha').value,
        };

        const cedulaPaciente = formData.paciente;
        const matriculaOdontologo = formData.odontologo;

        fetch(`/pacientes/cedula/${cedulaPaciente}`)
            .then(response => {
                if (!response.ok) {
                    alert("Paciente no encontrado");
                    throw new Error('Paciente no encontrado');
                }
                return response.json();
            })
            .then(paciente => {
                return fetch(`/odontologos/matricula/${matriculaOdontologo}`)
                    .then(response => {
                        if (!response.ok) {
                            alert("Odontologo no encontrado");
                            throw new Error('Odontólogo no encontrado');
                        }
                        return response.json();
                    })
                    .then(odontologo => {
                        const updatedFormData = {
                            id: turnoId,
                            paciente: {
                                id: paciente.id,
                                nombre: paciente.nombre,
                                apellido: paciente.apellido,
                                fechaIngreso: paciente.fechaIngreso,
                                cedula: paciente.cedula,
                                domicilio: {
                                    calle: paciente.domicilio.calle,
                                    numero: paciente.domicilio.numero,
                                    localidad: paciente.domicilio.localidad,
                                    provincia: paciente.domicilio.provincia
                                },
                                email: paciente.email
                            },
                            odontologo: {
                                id: odontologo.id,
                                nombre: odontologo.nombre,
                                apellido: odontologo.apellido,
                                matricula: odontologo.matricula,
                            },
                            fecha: document.querySelector('#fecha').value,
                        };

                        const url = '/turnos';
                        const settings = {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify(updatedFormData)
                        };

                        return fetch(url, settings);
                    });
            })
            .then(response => {
                if (!response.ok) {
                    alert('Error al modificar el turno')
                    throw new Error('Error al modificar el turno');
                }
                console.log('Turno modificado con éxito');
                window.location.href = '/get_turnos.html';
            })
    });

    function findBy(id) {
        const url = '/turnos/' + id;
        const settings = {
            method: 'GET'
        }
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let turno = data;
                document.querySelector('#turno_id').value = turno.id;
                document.querySelector('#paciente').value = turno.paciente.cedula;
                document.querySelector('#odontologo').value = turno.odontologo.matricula;
                document.querySelector('#fecha').value = turno.fecha;
                document.querySelector('#div_turno_updating').style.display = "block";
            }).catch(error => {
            alert("Error: " + error);
        });
    }

    window.findBy = findBy;
});

