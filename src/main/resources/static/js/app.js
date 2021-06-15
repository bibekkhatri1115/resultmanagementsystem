function loadTable(url) {
    var $block = $("#table-block");
    $block.html("<h1>Loading ....</h1>");
    $.get("/" + url + "/table", function(data) {
        $block.html(data);
    });
}

function loadComboBox(params, callback) {
    $.getJSON(params.uri, function(data) {
        $el = typeof params.el === "object" ? params.el : $(params.el);
        $el.find("option").remove();
        $el.append('<option value="">' + params.title + "</option>");
        $.each(data, function(i, row) {
            var $option = $("<option/>");
            if (params.row != undefined) {
                if (params.row.brand === row.id) {
                    $option.attr("selected", true);
                }
            }
            $option
                .val(row[params.keyname])
                .text(row[params.valuename])
                .appendTo($el);
        });
        if (callback !== undefined) {
            callback();
        }
    });
}

function mapData(data) {
    var $inputs = $("#form-dialog input");
    $.each($inputs, function(i, row) {
        var $input = $(row);
        var $name = $input.prop("name");

        if ($input.prop("type") === "text") {
            $input.val(data[$name]);
        } else if ($input.prop("type") === "checkbox") {
            $input.attr("checked", data[$name] === true);
        }
    });
    $("#form-dialog").modal();
}

function saveForm($uri, dialog, form) {
    $("#" + form).on("submit", function() {
        $.post("/" + $uri + "/save-json", $(this).serialize(), function(data) {
            if (data === true) {
                $("#" + dialog).modal("hide");
                loadTable($uri);
            }
        });
        return false;
    });
}

function loadCategory() {
    loadComboBox({
        uri: "/categories/json",
        title: "Select Category",
        el: "#form-category-id",
        keyname: "id",
        valuename: "name"
    });
}

function loadUnit() {
    loadComboBox({
        uri: "/units/json",
        title: "Select Unit",
        el: "#form-unit-id",
        keyname: "id",
        valuename: "name"
    });
}

function loadBrand() {
    loadComboBox({
        uri: "/brands/json",
        title: "Select Brand",
        el: "#form-brand-id",
        keyname: "id",
        valuename: "name"
    });
}

function createDataTable(param) {
    $(param).DataTable();
}

$(function() {
    $("select").select2({
        width: null,
        theme: "bootstrap"
    });

    $(".date-time-picker").datetimepicker({
        format: "YYYY-MM-DD HH:mm",
        minDate: new Date()
    });

    $(".date-picker").datepicker({
        format: "yyyy-mm-dd",
        startDate: new Date(),
        todayHighlight: true,
        autoClose: true
    });

    $(".date-picker").on("changeDate", function() {
        $(this).datepicker("hide");
    });

});