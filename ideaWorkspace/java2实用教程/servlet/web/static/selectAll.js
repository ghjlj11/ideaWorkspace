
function selectAll(){
    $.post({
        url: "/selectAll",
        success: function (data){
            console.log(data);
            let k = JSON.parse(data);
            let parse = k.students;
            let clazz = k.clazz;
            console.log("-------------------");
            console.log(clazz);
            let html = "";
            for (let i = 0; i < parse.length; i++) {
                html += "<tr>"
                + "<td>" + parse[i].username + "</td>"
                + "<td>" + parse[i].no + "</td>"
                + "<td>" + parse[i].email + "</td>"
                + "<td>" + parse[i].phone + "</td>"
                + "<td>" + parse[i].birthdate + "</td>"
                + "<td>" + clazz[parse[i].clazz_id].name + "</td>"
                + "<td>" + `<button onclick="del('${parse[i].id}')">` + "delete" + "</button>" + "&nbsp;|&nbsp;"
                    + `<a href="/toModify?id=${parse[i].id}">` + "<button>" + "modify" + "</button>" + "</a>" + "</td>"
                + "</tr>"
            }
            $("#body").html(html);
        }
    });
}

function del(i){
    console.log(i);
    $.get({
        url:"/delete",
        data: {"id":i},
        success: function (){
            selectAll();
            alert("success!");
        }
    });
}