var login = {
    init : function () {
        var _this = this;

        $('#btn-signin').on('click', function () {
            _this.getUsername();
        });

    },

    getUsername : function () {
        var data = {
            userName: $('#user_name').val()
        };

        $.ajax({
            type: 'GET',
            url: '/username',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    }

};

login.init();