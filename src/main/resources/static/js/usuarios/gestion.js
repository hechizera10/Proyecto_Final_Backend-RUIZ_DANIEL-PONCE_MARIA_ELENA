document.addEventListener("DOMContentLoaded", function() {
    fetch("/user/data")
        .then(response => response.json())
        .then(data => {
            const userName = document.getElementById("usuario");

            if (userName) {
                userName.innerHTML = data.userName;
            }

        }
    );


    fetch("/user/roles")
        .then(response => response.json())
        .then(roles => {
            const contenido = document.getElementById("contenido-dinamico");
            const navegacion = document.getElementById("navegacion");

            if (!roles.includes("ROLE_ADMIN")) {

                navegacion.innerHTML +=
                    '<li class="nav-item dropdown">' +
                    '    <a class="nav-link dropdown-toggle" href="get_turnos.html" role="button" data-bs-toggle="dropdown" aria-expanded="false">Turnos</a>' +
                    '    <ul class="dropdown-menu">' +
                    '        <li><a class="dropdown-item" href="post_turnos.html">Guardar</a></li>' +
                    '        <li><a class="dropdown-item" href="get_turnos.html">Listar</a></li>' +
                    '    </ul>' +
                    '</li>' +
                    '<li><a class="btn btn-danger" href="/logout">Salir</a></li>';

                if (contenido) {
                    contenido.innerHTML +=
                        '<li><a class="btn btn-success py-5 fw-bold" href="get_turnos.html" id="abm-turnos">ABM de turnos</a></li>';
                }} else {
                navegacion.innerHTML +=
                    '<li class="nav-item dropdown">' +
                    '    <a class="nav-link dropdown-toggle" href="get_pacientes.html" role="button" data-bs-toggle="dropdown" aria-expanded="false">Pacientes</a>' +
                    '    <ul class="dropdown-menu">' +
                    '        <li><a class="dropdown-item" href="post_pacientes.html">Guardar</a></li>' +
                    '        <li><a class="dropdown-item" href="get_pacientes.html">Listar</a></li>' +
                    '    </ul>' +
                    '</li>' +
                    '<li class="nav-item dropdown">' +
                    '    <a class="nav-link dropdown-toggle" href="get_odontologos.html" role="button" data-bs-toggle="dropdown" aria-expanded="false">Odontólogos</a>' +
                    '    <ul class="dropdown-menu">' +
                    '        <li><a class="dropdown-item" href="post_odontologos.html">Guardar</a></li>' +
                    '        <li><a class="dropdown-item" href="get_odontologos.html">Listar</a></li>' +
                    '    </ul>' +
                    '</li>' +
                    '<li class="nav-item dropdown">' +
                    '    <a class="nav-link dropdown-toggle" href="get_turnos.html" role="button" data-bs-toggle="dropdown" aria-expanded="false">Turnos</a>' +
                    '    <ul class="dropdown-menu">' +
                    '        <li><a class="dropdown-item" href="post_turnos.html">Guardar</a></li>' +
                    '        <li><a class="dropdown-item" href="get_turnos.html">Listar</a></li>' +
                    '    </ul>' +
                    '</li>' +
                    '<li><a class="btn btn-danger" href="/logout">Salir</a></li>';
                if (contenido) {
                    contenido.innerHTML +=
                        '<li><a class="btn btn-info py-5 text-white fw-bold" href="get_pacientes.html" id="abm-pacientes">ABM de pacientes</a></li>' +
                        '<li><a class="btn btn-primary py-5 fw-bold" href="get_odontologos.html" id="abm-odontologos">ABM de odontólogos</a></li>' +
                        '<li><a class="btn btn-success py-5 fw-bold" href="get_turnos.html" id="abm-turnos">ABM de turnos</a></li>';
                }
                }
        });
});