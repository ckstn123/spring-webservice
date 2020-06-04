var post = {
    init : function(){
        var _this = this;
        console.log(this);
        $('#btn-list').on('click', function(){
            _this.list();
        });
        $('#btn-modify').on('click', function(){
            _this.modify();
        });
        $('#btn-delete').on('click', function(){
            _this.delete();
        });
        $('#btn-replyAdd').on('click', function(){
            _this.replyAdd();
        });
        $('#btn-replyModify').on('click', function(){
            _this.replyModify();
        });
        $('#btn-replyDelete').on('click', function(){
            _this.replyDelete();
        });
        $(".reply").on("click", function () {
            _this.replyInfo();
        });
    },
    list : function() {
        window.location.href = '/';
    },
    modify : function () {
        if(confirm('게시글을 수정하시겠습니까?')) {
            var data = {
                id: $('#id').val(),
                title: $('#title').val(),
                author: $('#author').val(),
                content: $('#content').val()
            };

            $.ajax({
                type: 'PUT',
                url: '/posts',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('글이 수정되었습니다.');
                location.reload();
            }).fail(function (error) {
                alert(error);
            });
        }
    },

    delete : function () {
        if(confirm('게시글을 삭제하시겠습니까?')) {
            var data = $('#delete-id').val();

            $.ajax({
                type: 'DELETE',
                url: '/posts',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: data
            }).done(function () {
                alert('글이 삭제되었습니다.');
                location.href = '/';
            }).fail(function (error) {
                alert(error);
            });
        }
    },

    replyAdd : function () {
        // 화면으로부터 입력 받은 변수값의 처리
        var data = {
            postNo : $('#postNo').val(),
            replyContent : $('#newReplyContent').val(),
            replyWriter : $('#newReplyWriter').val()
        };

        // AJAX 통신 : POST
        $.ajax({
            type : "post",
            url : "/replies",
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            // 성공적인 댓글 등록 처리 알림
            alert("댓글 등록 완료!");
            location.reload();
            data.replyContent.val(""); // 댓글 내용 초기화
            data.replyWriter.val(""); // 댓글 작성자 초기화
        }).fail(function (error) {
            alert(error);
        });
    },

    replyModify : function () {
        if(confirm('댓글을 수정하시겠습니까?')) {
            var data = {
                replyNo: $('#replyNo').val(),
                replyContent: $('#replyContent').val(),
                replyWriter: $('#replyWriter').val()
            };
            console.log(data);
            $.ajax({
                type: "PUT",
                url: "/replies",
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                // 성공적인 댓글 등록 처리 알림
                alert("댓글 수정 완료!");
                location.reload();
            }).fail(function (error) {
                alert(error);
            });

        }
    },

    replyDelete : function () {
        if(confirm('댓글을 삭제하시겠습니까?')) {
            var data = $('#replyNo').val();

            console.log(data);
            $.ajax({
                type: "DELETE",
                url: "/replies",
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: data
            }).done(function () {
                // 성공적인 댓글 등록 처리 알림
                alert("댓글 삭제 완료!");
                location.reload();
            }).fail(function (error) {
                alert(error);
            });
        }
    },
    replyInfo : function () {
        var reply = $(".reply");

        var replyNo = reply.attr("data-replyNo");
        var replyText = reply.find(".replyText").text();
        var replyWriter = reply.find(".replyWriter").text();
        console.log(reply);
        $("#replyNo").val(replyNo);
        $("#replyText").val(replyText);
        $("#replyWriter").val(replyWriter);
    }
};

post.init();