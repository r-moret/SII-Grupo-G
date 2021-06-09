$(document).ready(function () {
    $('#matriculas-table').DataTable({
        dom: "<f<t>p>",
        language: {
            searchPlaceholder: "Buscar...",
            search: "",
            lengthMenu: "",
            zeroRecords: "No hay nada que mostrar, lo siento.",
            info: "",
            infoEmpty: "",
            infoFiltered: "",
            paginate: {
                next: "Siguiente",
                previous: "Anterior"
            },

        }
    });
    $('#grupos-table').DataTable({
        dom: "<f<t>p>",
        language: {
            searchPlaceholder: "Buscar...",
            search: "",
            lengthMenu: "",
            zeroRecords: "No hay nada que mostrar, lo siento.",
            info: "",
            infoEmpty: "",
            infoFiltered: "",
            paginate: {
                next: "Siguiente",
                previous: "Anterior"
            },

        }
    });
    $('#expedientes-table').DataTable({
        dom: "<f<t>p>",
        language: {
            searchPlaceholder: "Buscar...",
            search: "",
            lengthMenu: "",
            zeroRecords: "No hay nada que mostrar, lo siento.",
            info: "",
            infoEmpty: "",
            infoFiltered: "",
            paginate: {
                next: "Siguiente",
                previous: "Anterior"
            },

        }
    });
    $('#alumnos-table').DataTable({
        dom: "<f<t>p>",
        language: {
            searchPlaceholder: "Buscar...",
            search: "",
            lengthMenu: "",
            zeroRecords: "No hay nada que mostrar, lo siento.",
            info: "",
            infoEmpty: "",
            infoFiltered: "",
            paginate: {
                next: "Siguiente",
                previous: "Anterior"
            },

        }
    });
    $('#asignaturas-table').DataTable({
        dom: "<f<t>p>",
        language: {
            searchPlaceholder: "Buscar...",
            search: "",
            lengthMenu: "",
            zeroRecords: "No hay nada que mostrar, lo siento.",
            info: "",
            infoEmpty: "",
            infoFiltered: "",
            paginate: {
                next: "Siguiente",
                previous: "Anterior"
            },

        }
    });
    $('#asignaturasPorMatriculas-table').DataTable({
        dom: "<f<t>p>",
        language: {
            searchPlaceholder: "Buscar...",
            search: "",
            lengthMenu: "",
            zeroRecords: "No hay nada que mostrar, lo siento.",
            info: "",
            infoEmpty: "",
            infoFiltered: "",
            paginate: {
                next: "Siguiente",
                previous: "Anterior"
            },

        }
    });
    $('.dataTables_length').addClass('bs-select');
});