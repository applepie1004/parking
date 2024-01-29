$(function(){
    let carQtRt = {}
    $.ajax({
        url: "/apply/data/get-qt-rt",
        type: 'POST',
        contentType : 'application/json',
        success: function (res, textStatus, jqXHR) {
            for(let _item of res.RESULT) {
                carQtRt[_item.qt] = _item.rt;
            }
            console.log(carQtRt)
        }
    })


    $('#carNum1').focus();

    $('#carNum1').keyup((e) => {
        if(e.target.value != '') {
            e.target.value = (e.target.value+'').split('')[0]
            $('#carNum2').focus();
        }
    });
    $('#carNum2').keyup((e) => {
        if(e.target.value == '') {
            $('#carNum1').focus();
            return;
        }
        e.target.value = (e.target.value+'').split('')[0]
        $('#carNum3').focus();
    });
    $('#carNum3').keyup((e) => {
        if(e.target.value == '') {
            $('#carNum2').focus();
            return;
        }
        e.target.value = (e.target.value+'').split('')[0]
        $('#carNum4').focus();
    });
    $('#carNum4').keyup((e) => {
        if(e.target.value == '') {
            $('#carNum3').focus();
            return;
        }
        e.target.value = (e.target.value+'').split('')[0]
        $('.car-num-input').blur();


        let carNum = ''
        for(let i=1;i <= 4;i++) {
            carNum += $(`#carNum${i}`).val()
        }
        $('#detailCarNumber').text(carNum);
        $('.car-info').removeClass('hide');
        $('.display').removeClass('d-none');
    });

    $(".close").click(() => {
        $('.car-info').addClass('hide');
    })
    $('.display').click(() => {
        $('.car-info').removeClass('hide');
    })

    $('input[name="detail"]').keydown((e) => {
        let _val = e.target.value;
        let eng = /[a-zA-Z\s]/g;

        if(eng.test(_val)) {
            e.target.value = _val.replace(eng, '');
        }
    })

    $('#regBtn').click(() => {
        let _val = $('input[name="detail"]').val()
        let _val2 = $('#detailCarNumber').text();
        let _dong = $('input[name="dong"]').val()
        let _ho = $('input[name="ho"]').val()

        if(!_dong && !_ho) {
            alert('동호수를 입력해주세요');
            return;
        }

        let kor_num = /^[가-힣]{2}\d{1,2}[가-힣]$/;
        let num_kor = /^\d{2,3}[가-힣]$/
        if(kor_num.test(_val) || num_kor.test(_val)) {
            const data = JSON.stringify({dong: _dong, ho: _ho, carNum: _val+_val2});

            // 0. 저장된 차인지 정보 입수
            new Promise((resolve) => {
                $. ajax({
                    url: "/apply/data/car-exist",
                    type: 'POST',
                    contentType : 'application/json',
                    data: data,
                    success: function (res, textStatus, jqXHR) {
                        if(res.RESULT.isCarExist) {
                            alert('등록되었거나 등록 중인 차량입니다.')
                            return;
                        }else {
                            alert('등록 가능한 차량입니다.')
                            resolve() /* then 실행 */
                        }  /* -- END IF -- */
                    } /* -- END AJAX - SUCCESS -- */
                }) /* -- END AJAX -- */
            }).then(r =>{

                new Promise((resolve2) => {
                    // 1. 차량 대수 확인
                    $.ajax({
                        url: "/apply/data/get-car-rt",
                        type: 'POST',
                        contentType : 'application/json',
                        data: data,
                        success: function (res, textStatus, jqXHR) {
                            if(carQtRt[res.RESULT+1]) {
                                const result = confirm(`현재 ${res.RESULT}대의 차량이 등록되어있습니다.\n다음 차량의 등록 가격은 ${carQtRt[res.RESULT+1].toLocaleString('ko-KR')}원 입니다. \n등록하시겠습니까?`)
                                if(result) {
                                    resolve2(res.RESULT+1)
                                }
                                
                            } else {
                                alert('더 이상 차량을 등록할 수 없습니다.')
                            }
                        }
                    }) /* -- END AJAX -- */
                }).then((p) => {
                    // 2. 결제(추후 기능 작성)
                    // 미정
                    
                    // 3. 결제 완 > 등록(대기 상태), 결제 미완 > 첨부터(결제에 실패했습니다. 화면이 새로고침됩니다.)
                    $.ajax({
                        url: "/apply/data/save-car",
                        type: 'POST',
                        contentType : 'application/json',
                        data: data,
                        success: function (res, textStatus, jqXHR) {
                            console.log(res);
                        }
                    }) /* -- END AJAX -- */
                })
            })

            // 2. 결제
            // 3. 결제 완 > 등록(대기 상태), 결제 미완 > 첨부터(결제에 실패했습니다. 화면이 새로고침됩니다.)
        } else {
            alert('정확하지 않은 차량 번호 입니다.')
        }
    })

})