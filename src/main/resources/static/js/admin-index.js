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
            {title:"Car Number", field:"carNum" },
            {title:"status", field:"status",formatter: (e) =>{
                let data = e.getData()['status']
                if(data == 'N') {
                    return '거절'
                } else if(data == 'Y') {
                    return '허가'
                } else if(data == 'W') {
                    return '대기'
                }
            }},
            {title:"Date and Time", field:"regDt", formatter:"datetime", formatterParams:{
                inputFormat:"yyyy-MM-dd HH:mm:ss.u",
                outputFormat:"yyyy-MM-dd HH:mm",
                invalidPlaceholder:"(invalid date)",
                timezone:"Asia/Seoul",
            }},
        ],
        ajaxResponse : (e, res, result) => {
            return result.list;
        },
        ajaxRequesting: function(url, params) {
            params.searchText = ""
        },
    });
})