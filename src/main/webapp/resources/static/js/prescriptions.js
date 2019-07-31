function addPrescription() {
    let timesCount = $('*[type="time"]').length;
    $('<div class="form-group">\n' +
        '    <div class="input-group">\n' +
        '        <input name="prescriptionTimes[' + timesCount + '].time" type="time" class="form-control"\n' +
        '             />\n' +
        '        <div class="input-group-append">\n' +
        '            <button class="btn btn-primary" type="button" onclick="addPrescription()">\n' +
        '                +\n' +
        '            </button>' +
        '            <button class="btn btn-danger" type="button" onclick="removePrescription(this)">\n' +
        '                ×\n' +
        '            </button>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>'
    ).appendTo('.times').submit();
}

function removePrescription(diagnosis) {
    $(diagnosis).closest(".form-group").nextAll().each(function (index, sibl) {
        Array.from(sibl.getElementsByTagName("*"))
            .filter(e => e.hasAttribute("name"))
            .forEach(e => e.name = e.name.replace(/(\d+)/, function () {
                return arguments[1] * 1 - 1;
            }));
    });
    $(diagnosis).closest(".form-group").remove();
}