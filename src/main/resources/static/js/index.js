let user = {
    id: -1,
    balance: 0.00,
    username: "未登录"
};

$(".balance").text(user.balance.toFixed(2))

let getUserInfo = function (userInfoUrl) {
    $.ajax({
        url: userInfoUrl,
        success: function (data) {
            if (data.username) {
                user = data;
                $(".balance").text(user.balance.toFixed(2))
            }
        },
    })
}

let layTipRecharge;

let doRecharge = function (userInfoUrl, rechargeUrl) {
    getUserInfo(userInfoUrl)
    layTipRecharge = layer.open({
        type: 1,
        title: `<i class="fa fa-dollar" style="font-size: 25px; color: gold;"></i>  账户充值`,
        shadeClose: false,
        shade: 0.25,
        area: ['300px', '250px'],
        content: `<form action="${rechargeUrl}" method="post" onsubmit="rechargeSubmit(this); return false;"
              id="recharge-form" style="padding: 10px">
            <div class="alert alert-info">账户余额：<span style="color: #F70000">￥ <span class="balance">${user.balance.toFixed(2)}</span></span>
            </div>
            <div class="form-group">
                <div class="input-group input-group-lg">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="id-amount"><i class="fa fa-lg fa-dollar"></i></label>
                    </div>
                    <input type="hidden" name="next" value="${location.href}">
                    <input type="number" class="form-control" min="0.5" max="50000" size="7" step="0.5" required
                           name="amount" id="id-amount" autocomplete="off"
                           placeholder="请输入充值金额">
                </div>
            </div>
            <div class="form-group text-center">
                <input type="submit" class="btn btn-primary" value="充 值">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset" class="btn btn-outline-secondary" value="重 置">
            </div>
        </form>`
    });
}

let rechargeSubmit = function (form) {
    let formData = new FormData(form)
    $.ajax({
        url: form.action,
        type: "POST",
        data: {
            next: formData.get("next"),
            amount: formData.get("amount"),
        },
        beforeSend: function () {
            layer.msg("正在充值，请稍等。。。")
        },
        success: function (result) {
            // console.log(result)
            if (result.amount) {
                layer.msg(`成功充值 +￥${result.amount}`)
                getUserInfo(userInfoUrl)
            } else {
                layer.msg("充值失败")
            }
        },
        error: function () {
            layer.msg("400 充值失败")
        },
        complete: function () {
            if (layTipRecharge) {
                layer.close(layTipRecharge)
            }
        },
    })
    return false;
}

let layTipRePassword;
let doRePassword = function (userInfoUrl, rePasswordUrl) {
    getUserInfo(userInfoUrl)
    layTipRePassword = layer.open({
        type: 1,
        title: `<i class="fa fa-lock" style="font-size: 25px; color: #1E9FFF;"></i>  修改密码`,
        shadeClose: false,
        shade: 0.25,
        area: ['320px', '320px'],
        content: `<form action="${rePasswordUrl}" method="post" onsubmit="rePasswordSubmit(this); return false;"
      id="rePassword-form" style="padding: 10px">
    <input type="hidden" name="next" value="${location.href}">

    <div class="form-group">
        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <label class="input-group-text" for="old-password-id">
                    <i class="fa fa-lock fa-lg" aria-hidden="true"></i>
                </label>
            </div>
            <input type="password" minlength="4" maxlength="64" class="form-control" id="old-password-id" name="oldPassword" placeholder="请输入旧密码"
                   required autocomplete="off">
        </div>
    </div>

    <div class="form-group">
        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <label class="input-group-text" for="password1-id">
                    <i class="fa fa-lock fa-lg" aria-hidden="true"></i>
                </label>
            </div>
            <input type="password" minlength="4" maxlength="64" class="form-control" id="password1-id" name="password1" placeholder="请输入新密码"
                   required autocomplete="off">
        </div>
    </div>

    <div class="form-group">
        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <label class="input-group-text" for="password2-id">
                    <i class="fa fa-lock fa-lg" aria-hidden="true"></i>
                </label>
            </div>
            <input type="password" minlength="4" maxlength="64" class="form-control" id="password2-id" name="password2" placeholder="请确认新密码"
                   required autocomplete="off">
        </div>
    </div>

    <div class="form-group text-center">
        <input type="submit" class="btn btn-primary" value="修改密码">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" class="btn btn-outline-secondary" value="重 置">
    </div>
</form>`,
    });
}

let rePasswordSubmit = function (form) {
    let formData = new FormData(form)
    let oldPassword = formData.get("oldPassword")
    let password1 = formData.get("password1")
    let password2 = formData.get("password2")
    if (password1 !== password2) {
        layer.msg('新密码前后不一致', function () {
        });
        return false;
    }
    $.ajax({
        url: form.action,
        type: "POST",
        data: {
            next: formData.get("next"),
            oldPassword: oldPassword,
            password1: password1,
            password2: password2,
        },
        beforeSend: function () {
            layer.msg("正在修改密码，请稍等。。。")
        },
        success: function (result) {
            // console.log(result)
            if (result.status === 200) {
                layer.msg(`密码已修改成功`)
                getUserInfo(userInfoUrl)
            } else if (result.status === -1) {
                layer.msg(`${result.error}`, function(){
                });
            } else {
                layer.msg("密码修改失败")
            }
        },
        error: function () {
            layer.msg("400 密码修改失败")
        },
        complete: function () {
            if (layTipRePassword) {
                layer.close(layTipRePassword)
            }
        },
    })
    return false;
}
