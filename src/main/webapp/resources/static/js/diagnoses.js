function addDiagnosis() {
    let diagnosisCount = $('*[placeholder="diagnosis"]').length;
    $('<div class="form-group">\n' +
        '    <div class="input-group">\n' +
        '        <input name="diagnoses[' + diagnosisCount + '].name" type="text" class="form-control"\n' +
        '               placeholder="diagnosis"/>\n' +
        '        <div class="input-group-append">\n' +
        '            <button class="btn btn-primary" type="button" onclick="addDiagnosis()">\n' +
        '                +\n' +
        '            </button>' +
        '            <button class="btn btn-danger" type="button" onclick="removeDiagnosis(this)">\n' +
        '                ×\n' +
        '            </button>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <textarea name="diagnoses[' + diagnosisCount + '].comment" class="form-control"\n' +
        '              rows="0.5" placeholder="comment"></textarea>\n' +
        '</div>'
    ).appendTo('.fields').submit();
}

function removeDiagnosis(diagnosis) {
    $(diagnosis).closest(".form-group").nextAll().each(function (index, sibl) {
        Array.from(sibl.getElementsByTagName("*"))
            .filter(e => e.hasAttribute("name"))
            .forEach(e => e.name = e.name.replace(/(\d+)/, function () {
                return arguments[1] * 1 - 1;
            }));
    });
    $(diagnosis).closest(".form-group").remove();
}