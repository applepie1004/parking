"use strict";

$(function (){


    const table = new Tabulator("#table", {
        height: "100%",
        ajaxURL: "/adm/data/get-list",
        ajaxConfig: "POST",
        ajaxContentType: "json",           //load row data from array
        layout:"fitColumns",      //fit columns to width of table
        responsiveLayout:"hide",  //hide columns that don't fit on the table
        addRowPos:"top",          //when adding a new row, add it to the top of the table
        pagination:"local",       //paginate the data
        paginationSize:20,         //allow 7 rows per page of data
        placeholder: 'No Data',
        selectable: 1,
        initialSort:[             //set the initial sort order of the data
            {column:"status", dir:"asc"},
        ],
        columns:[                 //define the table columns
            {title:"aptId", field:"aptId", visible:false },
            {title:"차량번호", field:"carNum" },
            {title:"상태", field:"status",formatter: (e) =>{
                let data = e.getData()['status']
                if(data == 'N') {
                    return '거절'
                } else if(data == 'Y') {
                    return '허가'
                } else if(data == 'W') {
                    return '대기'
                }
            }, editor:"list", editorParams:{values:{'N':'거절','Y':'허가','W':'대기'}}},
            {title:"등록시간", field:"regDt",
                formatter:"datetime", formatterParams:{
                    inputFormat:"yyyy-MM-dd'T'HH:mm:ss.SSSZZ",
                    outputFormat:"yyyy-MM-dd HH:mm",
                    invalidPlaceholder:"(invalid date)",
                    timezone:"Asia/Seoul",
                }
            },
        ],
        ajaxResponse : (e, res, result) => {
            return result.list;
        },
        ajaxRequesting: function(url, params) {
            params.searchText = ""
        },
        footerElement:
            "<button id='btnUpdate' class='btn btn-sm btn-light btn-outline-dark me-1'><i class='fa-solid fa-plus'></i> 수정</button>",
    });


    table.on("tableBuilt", function(){
        //등록 버튼
        $("#btnUpdate").click(function(e) {
            const editCellList = [];
            if(table.getEditedCells().length > 0) {
                for(let cell of table.getEditedCells()) {
                    editCellList.push(cell.getData())
                }
                console.log(editCellList);
                $.ajax({
                    url: '/adm/data/change/status',
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(editCellList),
                    success: function(response) {
                        Swal.fire({
                            icon: 'success',
                            title: '수정되었습니다.',
                            text: '차량의 허가 상태가 변경되었습니다.',
                        });
                    },
                    error: function(e) {
                        alert('Error: ' + e);
                    }
                });
            } else {
                Swal.fire("", "수정된 정보가 없습니다. \n정보를 변경해주세요. ", "info");
            }
        });
    });
})